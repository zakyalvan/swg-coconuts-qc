package com.swg.sms.format;

import com.swg.sms.action.param.MapParameter;
import com.swg.sms.action.param.NumberParameter;
import com.swg.sms.action.param.Parameter;
import com.swg.sms.action.param.StringParameter;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Implementasi sederhana {@link Parser}. Kelas object yang digunakan untuk
 * memparsing isi pesan masuk sebelum action dieksekusi. Proses parsing ini dihint oleh
 * object {@link Format}, yaitu format valid yang dapat diterima oleh action.
 *
 * @author zakyalvan
 */
@Component("simpleParser")
public class SimpleParser implements Parser {
    private Logger logger = Logger.getLogger(getClass());

    private String delimiter = " ";

    public SimpleParser() {
    }

    public SimpleParser(String delimiter) {
        this.delimiter = delimiter;
    }

    /**
     * TODO Sempurnakan method ini biar bisa nge-handle advance use case.
     * Misalnya string normalization (auto-correct) atau nilai tambah lainnya.
     */
    @Override
    public Result parse(String payload, Format format) throws ParsingException {
        logger.info("Parsing message payload.");

        /**
         * TODO Perbaiki cara split ini. Misalnya, split parameter string yang berspasi.
         */
        String[] payloadParts = payload.split(delimiter);

        logger.debug("Payload parts length : " + payloadParts.length);
        if (payloadParts.length != format.getModel().countParameters() + 1) {
            /**
             * TODO Extends functionality di sini.
             */
            logger.error("Pesan tidak valid. Jumlah bagian dalam isi pesan tidak sama dengan jumlah bagian yang dibutuhkan dalam format");
            throw new ParsingException("Pesan tidak valid. Jumlah bagian dalam isi pesan tidak sama dengan jumlah bagian yang dibutuhkan dalam format.", this);
        }

        String keyword = null;
        Set<Parameter<?>> parameters = new HashSet<Parameter<?>>();
        for (int i = 1; i <= payloadParts.length; i++) {
            if (format.getModel().getKeywordPosition() == i) {
                keyword = payloadParts[i - 1];
            } else {
                String parameterName = format.getModel().getParameterNameAt(i);
                String parameterValue = payloadParts[i - 1];
                Class<? extends Parameter<?>> parameterType = format.getModel().getParameterTypeAt(i);

                logger.debug("Process parameter (" + parameterName + ") with raw value " + parameterValue);
                if (StringParameter.class.isAssignableFrom(parameterType)) {
                    StringParameter stringParameter = new StringParameter(parameterName, parameterValue);
                    parameters.add(stringParameter);
                } else if (NumberParameter.class.isAssignableFrom(parameterType)) {
                    try {
                        Integer value = Integer.parseInt(parameterValue.trim());
                        parameters.add(new NumberParameter(parameterName, value));
                    } catch (NumberFormatException e) {
                        /**
                         * TODO Tambahin fitur koreksi disini untuk perbaikan :
                         * 		- Check apakah ada angka 0 yang keganti dengan huruf O, ganti dengan angka.
                         * 		- Ada character antara digit, jika ya hapus character tersebut.
                         * 		- dst.
                         */
                        logger.error("Format angka dalam parameter pesan tidak valid.");
                        throw new ParsingException("Format angka dalam parameter pesan tidak valid.", this);
                    }
                } else if (MapParameter.class.isAssignableFrom(parameterType)) {
                    /**
                     * TODO Implementasi buat map parameter.
                     */
                } else {
                    throw new ParsingException("Parameter type (" + parameterType + ") tidak dikenali.", this);
                }
            }
        }
        Result result = new Result(keyword, parameters);

        logger.info("Parsing result : " + result);
        return result;
    }
}
