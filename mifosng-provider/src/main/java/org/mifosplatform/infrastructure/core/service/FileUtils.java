package org.mifosplatform.infrastructure.core.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.mifosplatform.infrastructure.core.data.ApiParameterError;
import org.mifosplatform.infrastructure.core.domain.Base64EncodedImage;
import org.mifosplatform.infrastructure.core.exception.ImageDataURLNotValidException;
import org.mifosplatform.infrastructure.core.exception.ImageUploadException;
import org.mifosplatform.infrastructure.core.exception.PlatformApiDataValidationException;
import org.mifosplatform.infrastructure.documentmanagement.exception.DocumentManagementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lowagie.text.pdf.codec.Base64;

public class FileUtils {

    private final static Logger logger = LoggerFactory.getLogger(FileUtils.class);

    public static final String MIFOSX_BASE_DIR = System.getProperty("user.home") + File.separator + "billing";
   // public static final String MIFOSX_BASE_DIR = System.getProperty("user.home");

    public static Random random = new Random();

    /**
     * Generate a random String
     * 
     * @return
     */
    public static String generateRandomString() {
        String characters = "abcdefghijklmnopqrstuvwxyz123456789";
        int length = generateRandomNumber();
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = characters.charAt(random.nextInt(characters.length()));
        }
        return new String(text);
    }

    /**
     * Generate a random number between 5 to 16
     * 
     * @return
     */
    public static int generateRandomNumber() {
        Random randomGenerator = new Random();
        return randomGenerator.nextInt(11) + 5;
    }

    /**
     * Generate the directory path for storing the new document
     * 
     * @param entityType
     * @param entityId
     * @return
     */
    public static String generateFileParentDirectory(String entityType, Long entityId) {
        return FileUtils.MIFOSX_BASE_DIR + File.separator + ThreadLocalContextUtil.getTenant().getName().replaceAll(" ", "").trim()
                + File.separator + "documents" + File.separator + entityType + File.separator + entityId + File.separator
                + FileUtils.generateRandomString();
    }

    /**
     * Generate directory path for storing new Image
     */
    public static String generateClientImageParentDirectory(final Long resourceId) {
        return FileUtils.MIFOSX_BASE_DIR + File.separator + ThreadLocalContextUtil.getTenant().getName().replaceAll(" ", "").trim()
                + File.separator + "images" + File.separator + "clients" + File.separator + resourceId;
    }

    /**
     * @param uploadedInputStream
     * @param uploadedFileLocation
     * @return
     * @throws IOException
     */
    public static String saveToFileSystem(InputStream uploadedInputStream, String uploadedFileLocation, String fileName) throws IOException {
        String fileLocation = uploadedFileLocation + File.separator + fileName;
        OutputStream out = new FileOutputStream(new File(fileLocation));
        int read = 0;
        byte[] bytes = new byte[1024];

        while ((read = uploadedInputStream.read(bytes)) != -1) {
            out.write(bytes, 0, read);
        }
        out.flush();
        out.close();
        return fileLocation;
    }

    /**
     * @param base64EncodedImage
     * @param uploadedFileLocation
     * @param fileName
     * @return
     * @throws IOException
     */
    public static String saveToFileSystem(Base64EncodedImage base64EncodedImage, String uploadedFileLocation, String fileName)
            throws IOException {
        String fileLocation = uploadedFileLocation + File.separator + fileName + base64EncodedImage.getFileExtension();
        OutputStream out = new FileOutputStream(new File(fileLocation));
        byte[] imgBytes = Base64.decode(base64EncodedImage.getBase64EncodedString());
        out.write(imgBytes);
        out.flush();
        out.close();
        return fileLocation;
    }

    /**
     * @param fileSize
     * @param name
     */
    public static void validateFileSizeWithinPermissibleRange(Long fileSize, String name, int maxFileSize) {
        /**
         * Using Content-Length gives me size of the entire request, which is
         * good enough for now for a fast fail as the length of the rest of the
         * content i.e name and description while compared to the uploaded file
         * size is negligible
         **/
        if (fileSize != null && ((fileSize / (1024 * 1024)) > maxFileSize)) { throw new DocumentManagementException(name, fileSize,
                maxFileSize); }
    }

    /**
     * Validates that passed in Mime type maps to known image mime types
     * 
     * @param mimeType
     */
    public static void validateImageMimeType(String mimeType) {
        if (!(mimeType.equalsIgnoreCase("image/gif") || mimeType.equalsIgnoreCase("image/jpeg") || mimeType.equalsIgnoreCase("image/png"))) { throw new ImageUploadException(); }
    }

    /**
     * Extracts Image from a Data URL
     * 
     * @param mimeType
     */
    public static Base64EncodedImage extractImageFromDataURL(String dataURL) {
        String fileExtension = "";
        String base64EncodedString = null;
        String gifDataURLSnippet = "data:image/gif;base64,";
        String pngDataURLSnippet = "data:image/png;base64,";
        String jpegDataURLSnippet = "data:image/jpeg;base64,";

        if (dataURL.contains(gifDataURLSnippet)) {
            base64EncodedString = dataURL.replaceAll(gifDataURLSnippet, "");
            fileExtension = ".gif";
        } else if (dataURL.contains(pngDataURLSnippet)) {
            base64EncodedString = dataURL.replaceAll(pngDataURLSnippet, "");
            fileExtension = ".png";
        } else if (dataURL.contains(jpegDataURLSnippet)) {
            base64EncodedString = dataURL.replaceAll(jpegDataURLSnippet, "");
            fileExtension = ".jpeg";
        } else {
            throw new ImageDataURLNotValidException();
        }

        return new Base64EncodedImage(base64EncodedString, fileExtension);
    }

    public static void validateClientImageNotEmpty(String imageFileName) {
        List<ApiParameterError> dataValidationErrors = new ArrayList<ApiParameterError>();
        if (imageFileName == null) {
            StringBuilder validationErrorCode = new StringBuilder("validation.msg.clientImage.cannot.be.blank");
            StringBuilder defaultEnglishMessage = new StringBuilder("The parameter image cannot be blank.");
            ApiParameterError error = ApiParameterError.parameterError(validationErrorCode.toString(), defaultEnglishMessage.toString(),
                    "image");
            dataValidationErrors.add(error);
            throw new PlatformApiDataValidationException("validation.msg.validation.errors.exist", "Validation errors exist.",
                    dataValidationErrors);
        }
    }

    public static void deleteClientImage(final Long resourceId, final String location) {
        File fileToBeDeleted = new File(location);
        boolean fileDeleted = fileToBeDeleted.delete();
        if (!fileDeleted) {
            // no need to throw an Error, simply log a warning
            logger.warn("Unable to delete image associated with clients with Id " + resourceId);
        }
    }
}