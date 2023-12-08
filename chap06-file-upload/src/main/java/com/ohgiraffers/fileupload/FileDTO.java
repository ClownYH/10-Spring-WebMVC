package com.ohgiraffers.fileupload;

public class FileDTO {

    private String originFileName;

    private String savedName;

    private String filesPath;

    private String fileDescription;

    public FileDTO() {
    }

    public FileDTO(String originFileName, String savedName, String filesPath, String fileDescription) {
        this.originFileName = originFileName;
        this.savedName = savedName;
        this.filesPath = filesPath;
        this.fileDescription = fileDescription;
    }

    public String getOriginFileName() {
        return originFileName;
    }

    public void setOriginFileName(String originFileName) {
        this.originFileName = originFileName;
    }

    public String getSavedName() {
        return savedName;
    }

    public void setSavedName(String savedName) {
        this.savedName = savedName;
    }

    public String getFilesPath() {
        return filesPath;
    }

    public void setFilesPath(String filesPath) {
        this.filesPath = filesPath;
    }

    public String getFileDescription() {
        return fileDescription;
    }

    public void setFileDescription(String fileDescription) {
        this.fileDescription = fileDescription;
    }

    @Override
    public String toString() {
        return "FileDTO{" +
                "originFileName='" + originFileName + '\'' +
                ", savedName='" + savedName + '\'' +
                ", filesPath='" + filesPath + '\'' +
                ", fileDescription='" + fileDescription + '\'' +
                '}';
    }
}
