package com.barcodegenerator.barcode.Controller;


import com.barcodegenerator.barcode.generatorsample.QRCodeUtil;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

@RestController
    @RequestMapping("/barcodes")
    public class BarCodeController {

    /**
     * Generate ordinary QR code according to url
     *
     * usage in browser :
     *   http://localhost:8080/barcodes/createCommonQRCode?url=123123
     *
     *
     */
    @RequestMapping(value = "/createCommonQRCode", produces = MediaType.IMAGE_PNG_VALUE)
    public void createCommonQRCode(HttpServletResponse response, String url) throws Exception {
        ServletOutputStream stream = null;
        try {
            stream = response.getOutputStream();
            //Use tools to generate QR code
            QRCodeUtil.encode(url, stream);
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            if (stream != null) {
                stream.flush();
                stream.close();
            }
        }
    }

    /**
     * Generate QR code with logo according to url
     *
     * usage in browser :
     *
     *    http://localhost:8080/barcodes/createCommonQRCode?url=123123
     *
     */
    @RequestMapping(value = "/createLogoQRCode", produces = MediaType.IMAGE_PNG_VALUE)
    public void createLogoQRCode(HttpServletResponse response, String url) throws Exception {
        ServletOutputStream stream = null;
        try {
            stream = response.getOutputStream();
            String logoPath = Thread.currentThread().getContextClassLoader().getResource("").getPath()
                    + "templates" + File.separator + "logo.jpg";
            //Use tools to generate QR code
            QRCodeUtil.encode(url, logoPath, stream, true);
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            if (stream != null) {
                stream.flush();
                stream.close();
            }
        }
    }
    }

