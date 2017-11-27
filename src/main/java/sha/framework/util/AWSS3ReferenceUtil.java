package sha.framework.util;

import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

public class AWSS3ReferenceUtil {
    
	
//	@Autowired
    private AmazonS3 s3 = AmazonS3ClientBuilder.standard()
            .withCredentials(new InstanceProfileCredentialsProvider())
            .build();
//	
//	
//	public FWCheckResult checkFile(String bucketName, String s3key) {
//
//		if(s3.doesObjectExist(bucketName, s3key)){
//			return new FWCheckResult("MSG001", "01", true);
//		}
//		return new FWCheckResult("MSG001", "01", false);
//	}
//	
//	public List<FWCheckResult> checkFile(String bucketName, String[] s3key) {
//
//		List<FWCheckResult> list = new ArrayList<FWCheckResult>();
//		for(int i=0; i<s3key.length; i++) {
//			list.add(checkFile(bucketName, s3key[i]));
//		}
//		
//		return list;
//	}
//	  
//	public FWUploadResult uploadFile(String bucketName, String s3key, File file, boolean uwagakiFlg) {
//		FWUploadResult rs;
//		InputStream input;
//		try  {
//			input = new FileInputStream(file);
//			rs = uploadFile(bucketName, s3key, input, uwagakiFlg);
//		}catch(FileNotFoundException ex) {
//			rs = new FWUploadResult("MSG002", "02", false);
//		}
//		return rs;
//	}
//	
//	public List<FWUploadResult> uploadFile(String bucketName, String s3key, InputStream[] input, boolean uwagakiFlg) {
//
//		List<FWUploadResult> rsList = new ArrayList<FWUploadResult>();
//		for(int i=0; i<input.length; i++) {
//			rsList.add(uploadFile(bucketName, s3key, input[i], uwagakiFlg));
//		}
//
//		return rsList;
//	}
//	
//	public List<FWUploadResult> uploadFile(String bucketName, String s3key, File[] files, boolean uwagakiFlg) {
//
//		List<FWUploadResult> rsList = new ArrayList<FWUploadResult>();
//		for(int i=0; i<files.length; i++) {
//			rsList.add(uploadFile(bucketName, s3key, files[i], uwagakiFlg));
//		}
//
//		return rsList;
//	}
//
//	public FWUploadResult uploadFile(String bucketName, String s3key, InputStream input, boolean uwagakiFlg) {
//		FWUploadResult rs;
//		FWCheckResult check = checkFile(bucketName, s3key);
//		
//		if(check.isCheckRs() && !uwagakiFlg) {
//			rs = new FWUploadResult("MSG002", "02", false);
//		}else if(!check.isCheckRs()) {
//			rs = new FWUploadResult("MSG002", "02", false);
//		}else {
//			try  {
//	
//				PutObjectRequest request = new PutObjectRequest(bucketName, s3key, input, new ObjectMetadata());
//	
//				TransferManager tm =  TransferManagerBuilder.defaultTransferManager();
//	
//				Upload upload = tm.upload(request);
//	
//				long lastTransferred = 0;
//				while (!upload.isDone()) {
//					long transferred = upload.getProgress().getBytesTransferred();
//					lastTransferred = transferred;
//	
//					Thread.sleep(100);
//				}
//				upload.waitForCompletion();
//	
//			}catch(InterruptedException ex) {
//				rs = new FWUploadResult("MSG002", "02", false);
//			}finally {
//				s3.shutdown();
//				rs = new FWUploadResult("MSG001", "01", true);
//			}
//		}
//
//		return rs;
//	}
//
//	public FWDownloadResult downloadFile(String bucketName, String s3key, OutputStream out) {
//		FWDownloadResult rs;
//
//		GetObjectRequest req = new GetObjectRequest(bucketName, s3key);
//
//		S3Object object = s3.getObject(req);
//
//		try  {
//			InputStream in = object.getObjectContent();
//			copy(in, out);
//
//		}catch(IOException ex) {
//			rs = new FWDownloadResult("MSG002", "02", false);
//		}finally {
//			rs = new FWDownloadResult("MSG001", "01", true);
//			rs.setOutputStream(out);
//		}
//
//		return rs;
//	}
//
//	public FWDownloadResult downloadFile(String bucketName, String s3key, String dir) {
//		FWDownloadResult rs;
//
//		GetObjectRequest req = new GetObjectRequest(bucketName, s3key);
//
//		TransferManager tm = TransferManagerBuilder.defaultTransferManager();
//
//		try  {
//			//int totalWork = (int) object.getObjectMetadata().getContentLength();
//			File file = new File(dir, s3key);
//			Download download = tm.download(req, file);
//
//			long lastTransferred = 0;
//			while (!download.isDone()) {
//				long transferred = download.getProgress().getBytesTransferred();
//				System.out.printf("worked: %d%n", (int) (transferred - lastTransferred));
//				lastTransferred = transferred;
//
//				System.out.printf("progress %f%n",download.getProgress().getPercentTransferred());
//
//				Thread.sleep(100);
//			}
//			download.waitForCompletion();
//
//
//		}catch(InterruptedException ex) {
//			rs = new FWDownloadResult("MSG002", "02", false);
//		}finally {
//			//			  try {
//			//				  object.close();
//			//			  } catch (IOException e) {
//			//				  rs = new FWDownloadResult("MSG002", "02", false);
//			//			  }
//
//			rs = new FWDownloadResult("MSG001", "01", true);
//		}
//
//		return rs;
//	}
//
//	public FWDeleteResult deleteFile(String bucketName, String s3key) {
//		List<KeyVersion> keys = new ArrayList<KeyVersion>();
//		
//		FWDeleteResult rs = new FWDeleteResult("MSG001", "01", true);
//		KeyVersion kv = new KeyVersion(s3key);
//		keys.add(kv);
//
//		DeleteObjectsRequest request = new DeleteObjectsRequest(bucketName);
//		request.setKeys(keys);
//		DeleteObjectsResult delRs = s3.deleteObjects(request);
//		if(delRs.getDeletedObjects().isEmpty()) {
//			 rs = new FWDeleteResult("MSG002", "02", false);
//		}else {
//			rs = new FWDeleteResult("MSG001", "01", true);
//		}
//		return rs;
//	}
//	
//	public List<FWDeleteResult> deleteFile(String bucketName, String[] s3key) {
//		List<FWDeleteResult> list = new ArrayList<FWDeleteResult>();
//		for(int i=0; i<s3key.length; i++) {
//			list.add(deleteFile(bucketName, s3key[i]));
//		}
//		
//		return list;
//	}
//	
//	public List<FWFileNameResult> searchFiles(String bucketName, String prefix){
//		ListObjectsRequest request = 
//				new ListObjectsRequest(bucketName, prefix, null, "/", null);
//		
//		List<FWFileNameResult> listRs = new ArrayList<FWFileNameResult>();
//		ObjectListing list;
//		do {
//			list = s3.listObjects(request);
//			for (S3ObjectSummary s : list.getObjectSummaries()) {
//				FWFileNameResult rsFile = new FWFileNameResult();
//				rsFile.setBucketName(bucketName);
//				rsFile.setKey(s.getKey());
//				rsFile.setSize(s.getSize());
//				listRs.add(rsFile);
//			}
//			request.setMarker(list.getNextMarker());
//		} while (list.isTruncated());
//		
//		return listRs;
//
//	}
//	
//	public List<String> searchFolders(String bucketName, String prefix){
//		ListObjectsRequest request = 
//				new ListObjectsRequest(bucketName, prefix, null, "/", null);
//		
//		List<String> listRs = new ArrayList<String>();
//		ObjectListing list = s3.listObjects(request);
//		do {
//			List<String> folders = list.getCommonPrefixes(); //フォルダー（共通のprefix）一覧
//			listRs.addAll(folders);
//
//			list = s3.listNextBatchOfObjects(list);
//		} while (list.getMarker() != null);
//		
//		return listRs;
//
//	}
//
//
//	private void copy(InputStream in, OutputStream out) throws IOException {
//		byte[] buff = new byte[1024];
//		for (int len = in.read(buff); len > 0; len = in.read(buff)) {
//			out.write(buff, 0, len);
//		}
//	}


}
