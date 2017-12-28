package sha.framework.util.aws;

/**
 * Configuration for AWS S3 client.
 * 
 * @author Fast Retailing
 * @version $Revision$
 */
//@Configuration
//@EnableConfigurationProperties(AwsS3Properties.class)
public class AwsS3Configuration {
    
//    /**
//     * Configuration properties.
//     */
//    @Autowired
//    private AwsS3Properties awsS3Properties;
//    
//    /**
//     * Builds a client with the configure properties.
//     * 
//     * @return AWS S3 Client instance to make API calls with.
//     */
//    @Bean
//    public AmazonS3 awsS3Client() {
//            
//        ClientConfiguration clientConfiguration = new ClientConfiguration();
//        clientConfiguration
//            .withClientExecutionTimeout(awsS3Properties.getClientExecutionTimeout())
//            .withConnectionMaxIdleMillis(awsS3Properties.getConnectionMaxIdleMillis())
//            .withConnectionTimeout(awsS3Properties.getConnectionTimeout())
//            .withMaxConnections(awsS3Properties.getMaxConnections())
//            .withRequestTimeout(awsS3Properties.getRequestTimeout())
//            .withSocketTimeout(awsS3Properties.getSocketTimeout());
//
//        return AmazonS3ClientBuilder.standard()
//                .withClientConfiguration(clientConfiguration)
//                .build();
//    }
//    
//    /**
//     * Construct a TransferManager with the default ExecutorService.
//     * @return TransferManager with configured AmazonS3 client. 
//     */
//    @Bean
//    public TransferManager transerManager() {
//        return TransferManagerBuilder.standard().withS3Client(awsS3Client()).build();
//    }
}
