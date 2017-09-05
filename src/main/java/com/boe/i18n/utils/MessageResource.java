package com.boe.i18n.utils;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.boe.sysmgr.cache.CacheComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

import com.boe.i18n.service.ResourceService;

/**
 * 取得资源数据
 * @author Robin
 *
 */
public class MessageResource extends AbstractMessageSource implements ResourceLoaderAware {

    @SuppressWarnings("unused")
    private ResourceLoader resourceLoader;
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private CacheComponent cacheComponent;
    
    /**
     * Map切分字符
     */
    protected final String MAP_SPLIT_CODE = "|";
    
    protected final String DB_SPLIT_CODE = "_";
    
    private final static Map<String, String> properties = new HashMap<String, String>();

	public MessageResource() {
//        reload();
    }
    private String getText(String code, Locale locale) {
        String localeCode = locale.getLanguage() + DB_SPLIT_CODE + locale.getCountry();
        String key = code + MAP_SPLIT_CODE + localeCode;
        String localeText = cacheComponent.loadTexts().get(key);//properties.get(key);
        String resourceText = code;
        
        if(localeText != null) {
            resourceText = localeText;
        }
        else {
            localeCode = Locale.ENGLISH.getLanguage();
            key = code + MAP_SPLIT_CODE + localeCode;
            localeText = properties.get(key);
            if(localeText != null) {
                resourceText = localeText;
            }
            else {
                try {
                    if(getParentMessageSource() != null) {
                        resourceText = getParentMessageSource().getMessage(code, null, locale);
                    }
                } catch (Exception e) {
                    logger.error("Cannot find message with code: " + code);
                }
            }
        }
        return resourceText;
    }
    
    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = (resourceLoader != null ? resourceLoader : new DefaultResourceLoader());
    }

    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {
        String msg = getText(code, locale);
        MessageFormat result = createMessageFormat(msg, locale);
        return result;
    }
    
    @Override
    public String resolveCodeWithoutArguments(String code, Locale locale) {
        String result = getText(code, locale);
        return result;
    }

}