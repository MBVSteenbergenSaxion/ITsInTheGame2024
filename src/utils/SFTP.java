package utils;
import com.jcraft.jsch.*;


public class SFTP {

    private static ChannelSftp setupJsch(String username, String pass, String ip) throws JSchException {

        JSch jsch = new JSch();

        java.util.Properties config = new java.util.Properties();
        config.put("StrictHostKeyChecking", "no");

        Session jschSession = jsch.getSession(username, ip);
        jschSession.setConfig(config);
        jschSession.setPassword(pass);
        jschSession.connect();

        return (ChannelSftp) jschSession.openChannel("sftp");
    }

    public static void downloadFile(String username, String pass, String ip) throws JSchException, SftpException {
        ChannelSftp channelSftp = setupJsch(username, pass, ip);
        channelSftp.connect();

        String remoteFile = "project/scores.csv";
        String localDir = "temp/";

        channelSftp.get(remoteFile, localDir + "scores.csv");
        channelSftp.exit();
    }

    public static void deleteFile(String username, String pass, String ip) throws JSchException, SftpException{
        ChannelSftp channelSftp = setupJsch(username, pass, ip);
        channelSftp.connect();

        String remoteFile = "project/scores.csv";

        channelSftp.rm(remoteFile);
        channelSftp.exit();
    }


    public static void putFile(String username, String pass, String ip) throws JSchException, SftpException {
        ChannelSftp channelSftp = setupJsch(username, pass, ip);
        channelSftp.connect();

        String localFile = "temp/scores.csv";
        String remoteDir = "project/";

        channelSftp.put(localFile, remoteDir + "scores.csv");
        channelSftp.exit();
    }

}
