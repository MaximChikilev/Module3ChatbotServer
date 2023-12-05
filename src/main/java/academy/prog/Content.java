package academy.prog;

import java.io.*;

public class Content {
    private int contentId;
    private String fileName;
    private String comment;
    private byte[] fileInByteRepresentation;

    public Content(String fileName, String comment) {
        this.fileName = fileName;
        this.comment = comment;
    }

    public Content(int contentId, String fileName, String comment, byte[] fileInByteRepresentation) {
        this.contentId = contentId;
        this.fileName = fileName;
        this.comment = comment;
        this.fileInByteRepresentation = fileInByteRepresentation;
    }

    public void uploadContent(String pathToWorkFolder) throws IOException {
        File file = new File(pathToWorkFolder + fileName);
        this.fileInByteRepresentation = new byte[(int) file.length()];
        try (InputStream fis = new FileInputStream(file)) {
            while (fis.read(fileInByteRepresentation) != -1) {

            }
        }
    }

    public void downloadContent(String pathToWorkFolder) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(pathToWorkFolder + fileName)) {
            fos.write(fileInByteRepresentation);
        }
    }

    public String getFileName() {
        return fileName;
    }

    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public byte[] getFileInByteRepresentation() {
        return fileInByteRepresentation;
    }

    public void setFileInByteRepresentation(byte[] fileInByteRepresentation) {
        this.fileInByteRepresentation = fileInByteRepresentation;
    }
}
