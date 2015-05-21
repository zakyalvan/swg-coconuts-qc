package com.swg.sms.format;

import com.swg.sms.action.Keyword;
import com.swg.sms.action.param.NumberParameter;
import com.swg.sms.action.param.Parameter;
import com.swg.sms.action.param.StringParameter;
import com.swg.sms.format.Format.FormatModel;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Ini representasi dari format sms yang dimengerti oleh system.
 * Setiap action memiliki satu object format ini (Atau dibikin
 * bisa lebih dari satu juga? Sebagai alternative. Untuk sementara satu
 * aja dulu kali ya). Format ini menunjukan bagaimana sususan {@link Keyword}
 * dan {@link Parameter} dalam satu action. Sebagai contoh, isi dari object ini adalah
 * <p>
 * {keyword}<space>{parameter:string:parameterName1}<space>{parameter:map:parameterName2}<space>{parameter:number:parameterName3}
 *
 * @author zakyalvan
 */
public final class SimpleFormatModel implements FormatModel, Serializable {
    public static final Pattern VALID_FORMAT_PATTERN =
            Pattern.compile(
                    "(\\G\\s*?\\{\\s*?(([keyword]{6,8}?)|([parmet]{8,10}\\s*?:\\s*?(string|number|map){1}\\s*?:\\s*?(\\w*?){1}))\\s*?\\}\\s*?)\\1*?",
                    Pattern.CASE_INSENSITIVE);
    private static final long serialVersionUID = -5492323427440482670L;
    private static Logger logger = Logger.getLogger(SimpleFormatModel.class);
    private String formatString;
    private Map<String, ParameterInfo> parameterInfoMap = new HashMap<String, ParameterInfo>();
    private SimpleFormatModel(String formatString) {
        this.formatString = formatString;
    }

    public static final SimpleFormatModel decodeFromString(String format) {
        if (format == null || format.length() == 0) {
            logger.error("Format parameter should not be null or zero length string.");
            throw new IllegalArgumentException("Format parameter should not be null or zero length string.");
        }
        // Trim dahulu fomatnya.
        format = format.trim();
        int formatLength = format.length();

        Matcher validPatternMatcher = VALID_FORMAT_PATTERN.matcher(format);

        int lastEnd = 0;
        int parameterCount = 0;
        boolean keywordPartFound = false;
        Set<ParameterInfo> parameterInfos = new HashSet<ParameterInfo>();
        while (validPatternMatcher.find()) {
            lastEnd = validPatternMatcher.end();

            if (validPatternMatcher.group(3) != null) {
                logger.debug("Keyword part (" + validPatternMatcher.group(3) + ") found start at index " + validPatternMatcher.start(3) + " and end at index " + validPatternMatcher.end(3));
                if (parameterCount > 0) {
                    logger.error("Invalid format. Keyword part found after " + parameterCount + " of parameter(s) definition. Keyword part should defined in first part");
                    throw new IllegalArgumentException(
                            "Invalid format. Keyword part found after " + parameterCount + " of parameter(s) definition. Keyword part should defined in first part");
                }

                if (!keywordPartFound) {
                    keywordPartFound = true;
                } else {
                    logger.error("Invalid format. Keyword part defined more than one. Only one keyword part allowed in valid format.");
                    throw new IllegalArgumentException(
                            "Invalid format. Keyword part already found before. Only one keypword part allowed in valid format.");
                }
            } else if (validPatternMatcher.group(4) != null) {
                logger.debug("Parameter part #" + (parameterCount + 1) + " (" + validPatternMatcher.group(4) + ") found.");
                String parameterType = validPatternMatcher.group(5);
                String parameterName = validPatternMatcher.group(6);

                Class<? extends Parameter<?>> type = null;
                if (parameterType.equalsIgnoreCase("string")) {
                    type = StringParameter.class;
                } else if (parameterType.equalsIgnoreCase("number")) {
                    type = NumberParameter.class;
                } else if (parameterType.equalsIgnoreCase("map")) {
                    //type = (Class<? extends Parameter<?>>) MapParameter.class;
                }

                parameterCount++;

                // Parameter count ditambah satu karena pada posisi 1 ada keyword.
                ParameterInfo parameterInfo = new ParameterInfo(parameterName, type, parameterCount + 1);
                if (!parameterInfos.add(parameterInfo)) {
                    logger.error("Invalid format. Parameter with name " + parameterName + " found more than once.");
                    throw new IllegalArgumentException("Invalid format. Parameter with name \"" + parameterName + "\" found more than once.");
                }
            }
        }

        if (lastEnd != formatLength) {
            logger.error("Invalid format. Terjadi kesalahan pada format yang didefinisikan " +
                    "(Dimulai sekitar part \"" + format.substring(lastEnd) + "\")");
            throw new IllegalArgumentException("Invalid format. Terjadi kesalahan pada format yang didefinisikan " +
                    "(Dimulai sekitar part \"" + format.substring(lastEnd) + "\")");
        }

        logger.debug(parameterInfos);

        SimpleFormatModel formatModel = new SimpleFormatModel(format);
        for (ParameterInfo parameterInfo : parameterInfos) {
            formatModel.parameterInfoMap.put(parameterInfo.getName(), parameterInfo);
        }
        return formatModel;
    }

    public String getFormatString() {
        return formatString;
    }

    /**
     * Retrieve position dari keyword.
     * Index ini ditentukan setelah format di split menjadi beberapa bagian
     * (token format) dan displit berdasarkan delimiter.
     *
     * @return Integer
     */
    public Integer getKeywordPosition() {
        return 1;
    }

    /**
     * Count seluruh parameter yang didefinisikan dalam format.
     *
     * @return
     */
    public Integer countParameters() {
        return parameterInfoMap.keySet().size();
    }

    /**
     * Retrieve parameter name yang terdefinisi dalam format.
     *
     * @return
     */
    public Set<String> getParametersName() {
        return parameterInfoMap.keySet();
    }

    public String getParameterNameAt(Integer position) {
        logger.debug("Retrieve parameter name at position : " + position);

        // Tambah satu, yaitu posisi keyword.
        if (parameterInfoMap.keySet().size() + 1 < position) {
            throw new IndexOutOfBoundsException("Posisi yang diminta lebih besar dari jumlah parameter yang ada.");
        }

        String parameterName = null;
        for (ParameterInfo parameterInfo : parameterInfoMap.values()) {
            logger.debug("Parameter in loop : " + parameterInfo);
            if (parameterInfo.getPosition() == position) {
                parameterName = parameterInfo.getName();
            }
        }
        return parameterName;
    }

    public Integer getParameterPosition(String name) {
        Integer position = 0;
        for (String parameterName : parameterInfoMap.keySet()) {
            if (parameterName.equalsIgnoreCase(name)) {
                position = parameterInfoMap.get(parameterName).getPosition();
            }
        }
        if (position == 0) {
            throw new IllegalArgumentException("Parameter with name '" + name + "' not found.");
        }
        return position;
    }

    public Class<? extends Parameter<?>> getParameterTypeAt(Integer position) {
        // Tambah satu, yaitu posisi keyword.
        if (parameterInfoMap.keySet().size() + 1 < position) {
            throw new IndexOutOfBoundsException("Posisi yang diminta lebih besar dari jumlah parameter yang ada.");
        }

        logger.debug("Retrieve parameter type at position : " + position);
        Class<? extends Parameter<?>> parameterType = null;
        for (ParameterInfo parameterInfo : parameterInfoMap.values()) {
            logger.debug("Item in loop : " + parameterInfo);
            if (parameterInfo.getPosition() == position) {
                parameterType = parameterInfo.getType();
            }
        }
        return parameterType;
    }

    public Class<? extends Parameter<?>> getParameterTypeFor(String name) {
        Class<? extends Parameter<?>> type = null;
        for (String parameterName : parameterInfoMap.keySet()) {
            if (parameterName.equalsIgnoreCase(name)) {
                type = parameterInfoMap.get(parameterName).getType();
            }
        }
        if (type == null) {
            throw new IllegalArgumentException("Parameter with name '" + name + "' not found.");
        }
        return type;
    }

    /**
     * Kelas model sederhana untuk nyimpan informasi parameter.
     *
     * @author zakyalvan
     */
    static class ParameterInfo {
        private final String name;
        private final Class<? extends Parameter<?>> type;
        private final Integer position;

        public ParameterInfo(String name, Class<? extends Parameter<?>> type, Integer position) {
            this.name = name;
            this.type = type;
            this.position = position;
        }

        public String getName() {
            return name;
        }

        public Class<? extends Parameter<?>> getType() {
            return type;
        }

        public Integer getPosition() {
            return position;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((name == null) ? 0 : name.toLowerCase().hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            ParameterInfo other = (ParameterInfo) obj;
            if (name == null) {
                if (other.name != null)
                    return false;
            } else if (!name.toLowerCase().equalsIgnoreCase(other.name.toLowerCase()))
                return false;
            return true;
        }

        @Override
        public String toString() {
            return "ParameterInfo [name=" + name + ", type=" + type
                    + ", position=" + position + "]";
        }
    }
}
