package sha.framework.util.aws;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import com.amazonaws.AmazonClientException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import com.amazonaws.services.s3.model.DeleteObjectsResult;
import com.amazonaws.services.s3.model.DeleteObjectsResult.DeletedObject;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.transfer.MultipleFileUpload;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.Upload;

/**
 * Common component <br>
 * AWS S3 file utility class.
 *
 * @author Fast Retailing
 * @version $Revision$
 */
@Component
public class AwsS3Utility {

    /** The delimiter for condensing common prefixes in the returned listing results. */
    private static final String AWS_S3_DELIMITER = "/";

    /** Provides the client for accessing the Amazon S3 web service. */
    @Autowired
    private AmazonS3 awsS3Client;

    /** High level utility for managing transfers to Amazon S3. */
    @Autowired
    private TransferManager transferManager;

    /**
     * Check the specified AWS S3 key whether it is exists.
     * @param bucketName AWS S3 bucket name
     * @param s3Key AWS S3 key
     * @return true when exist | false when not exist
     * @throws AwsS3SdkException If any errors occurred in Amazon SDK.
     */
    public boolean checkFile(String bucketName, String s3Key) throws AwsS3SdkException {

        try {
            if (awsS3Client.doesObjectExist(bucketName, s3Key)) {
                return true;
            }
        } catch (SdkClientException exception) {
            throw new AwsS3SdkException("S3 check file error - AWS SDK Exception.", exception);
        }
        return false;
    }

    /**
     * Check the specified AWS S3 key list whether they are exist.
     * @param bucketName AWS S3 bucket name.
     * @param s3KeyList AWS S3 key.
     * @return check result list.
     * @throws AwsS3SdkException If any errors occurred in Amazon SDK.
     */
    public List<Boolean> checkFiles(String bucketName,
            List<String> s3KeyList) throws AwsS3SdkException {

        List<Boolean> resultList = new ArrayList<Boolean>();
        if (s3KeyList == null || s3KeyList.isEmpty()) {
            return resultList;
        }

        for (String key : s3KeyList) {
            try {
                resultList.add(awsS3Client.doesObjectExist(bucketName, key));
            } catch (SdkClientException exception) {
                throw new AwsS3SdkException("S3 check files error - AWS SDK Exception.", exception);
            }
        }

        return resultList;
    }

    /**
     * Upload an input stream data to AWS S3 storage
     * (be care for the stream contents size before upload, 
     *  the stream contents will be buffered in memory 
     *  and could result in out of memory errors if it is 
     *  vary large data).
     * @param bucketName AWS S3 bucket name
     * @param s3Key File name to save
     * @param inputData Input stream data
     * @param overwriteFlag whether overwrite when file existing
     * @return true when success | false when not success
     * @throws AwsS3InvalidParameterException if any errors occurred in checking parameters.
     * @throws AwsS3FileAlreadyExistsException if the file to be uploaded already exists.
     * @throws AwsS3SdkException If any errors occurred in Amazon SDK.
     */
    public boolean upload(String bucketName,
            String s3Key, InputStream inputData, boolean overwriteFlag)
            throws AwsS3InvalidParameterException, AwsS3FileAlreadyExistsException,
            AwsS3SdkException {

        if (StringUtils.isEmpty(bucketName) || StringUtils.isEmpty(s3Key) || inputData == null) {
            throw new AwsS3InvalidParameterException(
                    "S3 upload Error - bucketName, s3Key, inputData must be non-empty.");
        }

        boolean checkResult = checkFile(bucketName, s3Key);
        if (checkResult && !overwriteFlag) {
            throw new AwsS3FileAlreadyExistsException("S3 upload Error - file already exists.");
        } else {
            try {
                PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, s3Key,
                        inputData, new ObjectMetadata());

                Upload upload = transferManager.upload(putObjectRequest);
                upload.waitForCompletion();

                if (!checkFile(bucketName, s3Key)) {
                    return false;
                }

                return true;
            } catch (SdkClientException | InterruptedException exception) {
                throw new AwsS3SdkException("S3 upload Error - AWS SDK Exception.", exception);
            }
        }
    }

    /**
     * upload a file to AWS S3 storage.
     * @param bucketName AWS S3 bucket name
     * @param s3Key File name to save
     * @param inputFile The specified upload file
     * @param overwriteFlag Whether overwrite when file existing
     * @return when success | false when not success
     * @throws AwsS3InvalidParameterException if any errors occurred in checking parameters.
     * @throws AwsS3FileNotFoundException if the file to be uploaded does not exist.
     * @throws AwsS3SdkException If any errors occurred in Amazon SDK.
     */

    public boolean uploadFile(String bucketName,
            String s3Key, File inputFile, boolean overwriteFlag)
            throws AwsS3InvalidParameterException, AwsS3FileNotFoundException,
            AwsS3FileAlreadyExistsException, AwsS3SdkException {

        if (StringUtils.isEmpty(bucketName) || StringUtils.isEmpty(s3Key) || inputFile == null) {
            throw new AwsS3InvalidParameterException(
                    "S3 upload Error - bucketName, s3Key, inputFile must be non-empty.");
        }

        if (!inputFile.exists()) {
            throw new AwsS3FileNotFoundException("S3 upload Error - file dose not exist.");
        }

        boolean checkResult = checkFile(bucketName, s3Key);

        if (checkResult && !overwriteFlag) {
            throw new AwsS3FileAlreadyExistsException("S3 upload Error - file already exists.");
        } else {
            try {
                PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, s3Key,
                        inputFile);

                Upload upload = transferManager.upload(putObjectRequest);
                upload.waitForCompletion();

                if (!checkFile(bucketName, s3Key)) {
                    return false;
                }

                return true;
            } catch (SdkClientException | InterruptedException exception) {
                throw new AwsS3SdkException("S3 upload Error - AWS SDK Exception.", exception);
            }
        }
    }

    /**
     * upload file list to AWS S3 storage.
     * @param bucketName AWS S3 bucket name
     * @param commonDirectory The common parent directory of files to upload. 
     * @param fileList Files to upload
     * @return List of boolean result
     * @throws AwsS3InvalidParameterException if any errors occurred in checking parameters.
     * @throws AwsS3SdkException if any errors occurred in Amazon SDK.
     */
    public List<S3FileSummary> uploadFiles(String bucketName, String virtualDirectoryKeyPrefix,
            File commonDirectory, List<File> fileList)
            throws AwsS3InvalidParameterException, AwsS3SdkException {

        if (StringUtils.isEmpty(bucketName)) {
            throw new AwsS3InvalidParameterException(
                    "S3 upload Error - bucketName must be non-empty.");
        }

        List<S3FileSummary> resultList = new ArrayList<S3FileSummary>();

        if (fileList == null || fileList.isEmpty() || fileList.size() == 0) {
            return resultList;
        }

        MultipleFileUpload uploadResult = transferManager.uploadFileList(bucketName,
                virtualDirectoryKeyPrefix,
                commonDirectory, fileList);
        
        try {
            uploadResult.waitForCompletion();
        } catch (AmazonClientException | InterruptedException exception) {
            throw new AwsS3SdkException("S3 upload Error - AWS SDK Exception.", exception);
        }
        
        try {
            S3SearchResult searchResult = searchFiles(bucketName, uploadResult.getKeyPrefix(),
                    null);
            resultList = searchResult.getS3FileNameResults();
        } catch (Exception e) {
            // do nothing.
        }
         
        return resultList;
    }

    /**
     * download an AWS S3 file, return a input stream data.
     * @param bucketName AWS S3 bucket name
     * @param s3Key The specified S3 key
     * @return {@link S3ObjectInputStream} 
     * @throws AwsS3InvalidParameterException if any errors occurred in checking parameters.
     * @throws AwsS3FileNotFoundException if the file to be downloaed does not exist.
     * @throws AwsS3SdkException If any errors occurred in Amazon SDK.
     */
    public InputStream download(String bucketName, String s3Key)
            throws AwsS3InvalidParameterException, AwsS3FileNotFoundException, AwsS3SdkException {

        if (StringUtils.isEmpty(bucketName) || StringUtils.isEmpty(s3Key)) {
            throw new AwsS3InvalidParameterException(
                    "S3 download Error - bucketName, s3Key must be non-empty.");
        }

        GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName, s3Key);

        try {

            S3Object s3Object = awsS3Client.getObject(getObjectRequest);

            if (s3Object == null) {
                throw new AwsS3FileNotFoundException(
                        "S3 download Error - download file is not found.");
            }

            return s3Object.getObjectContent();
        } catch (SdkClientException exception) {
            throw new AwsS3SdkException("S3 download Error - AWS SDK Exception.", exception);
        }
    }

    /**
     * delete an AWS S3 file from S3 storage.
     * @param bucketName AWS S3 bucket name.
     * @param s3Key The specified S3 key args list.
     * @return deleted keys. 
     * @throws AwsS3InvalidParameterException if any errors occurred in checking parameters.
     * @throws AwsS3SdkException If any errors occurred in Amazon SDK.
     */
    public List<String> deleteFile(String bucketName, String... s3Key)
            throws AwsS3InvalidParameterException, AwsS3SdkException {

        List<String> deletedKeys = new ArrayList<>();

        if (StringUtils.isEmpty(bucketName)) {
            throw new AwsS3InvalidParameterException(
                    "S3 delete Error - bucketName must be non-empty.");
        }

        if (s3Key.length == 0) {
            return deletedKeys;
        }

        DeleteObjectsRequest deleteObjectsRequest = new DeleteObjectsRequest(bucketName);

        try {
            DeleteObjectsResult deleteObjectsResult = awsS3Client
                    .deleteObjects(deleteObjectsRequest.withKeys(s3Key));

            return deleteObjectsResult.getDeletedObjects()
                    .stream()
                    .map(DeletedObject::getKey)
                    .collect(Collectors.toList());
        } catch (SdkClientException exception) {
            throw new AwsS3SdkException("S3 delete file Error - AWS SDK Exception.", exception);
        }
    }

    /**
     * Search files from AWS S3 storage.
     * @param bucketName AWS S3 bucket name
     * @param prefix The specified prefix
     * @param nextMarker The key marker indicating where listing results should begin
     *                     (null when beginning)
     * @return <@link S3SearchResult>
     * @throws AwsS3InvalidParameterException if any errors occurred in checking parameters.
     * @throws AwsS3SdkException If any errors occurred in Amazon SDK.
     */
    public S3SearchResult searchFiles(String bucketName, String prefix, String nextMarker)
            throws AwsS3InvalidParameterException, AwsS3SdkException {

        S3SearchResult s3SearchResult = new S3SearchResult();

        if (StringUtils.isEmpty(bucketName) || StringUtils.isEmpty(prefix)) {
            throw new AwsS3InvalidParameterException(
                    "S3 search file Error - bucketName, prefix must be non-empty.");
        }

        try {
            ListObjectsRequest listObjectsRequest = new ListObjectsRequest(bucketName, prefix,
                    nextMarker, AWS_S3_DELIMITER, null);
            ObjectListing objectListing = awsS3Client.listObjects(listObjectsRequest);

            List<S3FileSummary> s3FileSummaries = objectListing.getObjectSummaries()
                    .stream()
                    .map(objectSummary -> {
                        S3FileSummary s3FileSummary = new S3FileSummary(objectSummary.getKey(),
                                objectSummary.getSize());
                        return s3FileSummary;
                    })
                    .collect(Collectors.toList());

            s3SearchResult.setBucketName(bucketName);
            s3SearchResult.setS3FileNameResults(s3FileSummaries);
            s3SearchResult.setTruncated(objectListing.isTruncated());
            if (objectListing.isTruncated()) {
                s3SearchResult.setNextMarker(objectListing.getNextMarker());
            } else {
                s3SearchResult.setNextMarker(null);
            }
        } catch (SdkClientException exception) {
            throw new AwsS3SdkException("S3 search file Error - AWS SDK Exception.", exception);
        }
        return s3SearchResult;
    }
}
