package com.bsahu.chotaurl.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bsahu.chotaurl.bo.LongUrl;
import com.bsahu.chotaurl.bo.ShortUrl;
import com.bsahu.chotaurl.model.UrlEntity;
import com.bsahu.chotaurl.repository.UrlConverterRepository;
import com.bsahu.chotaurl.util.UrlConversionUtil;

/**
 * @author bsahu
 *
 */
@Service
public class UrlConverterServiceImpl implements UrlConverterService{
    Logger logger = LoggerFactory.getLogger(UrlConverterServiceImpl.class);

    @Autowired
    UrlConverterRepository urlConverterRepository;

    public List<UrlEntity> getAllUrls() {
        return urlConverterRepository.findAll();
    }

    public LongUrl getLongUrl(String shortenString) {
        
        Long id = UrlConversionUtil.getId(shortenString);
        logger.info("Converted Base 62 string "+shortenString+" to Base 10 id "+ id);
        UrlEntity urlEntity=this.addViewCount(id);
        return new LongUrl(urlEntity.getLongUrl());
    }

    public ShortUrl getShortUrl(LongUrl longUrl) {

        List<UrlEntity> savedUrls = null;
        savedUrls = isUrlExists(longUrl);

        UrlEntity savedUrl = null;

        if (savedUrls.isEmpty()) {
            logger.info("Inserting Url to database"+ longUrl.getLongUrl());
            savedUrl = this.save(longUrl);
            logger.debug(savedUrl.toString());
        }
        else {
        	logger.info("Url already exists "+ longUrl.getLongUrl());
            savedUrl = savedUrls.get(0);
        }

        String shortUrlText = UrlConversionUtil.getAlias(savedUrl.getId());
        savedUrl.setShortUrl(shortUrlText);
        this.save(savedUrl);
        
        return new ShortUrl(shortUrlText);
    }

    private UrlEntity addViewCount(Long id) {
        UrlEntity urlEntity = this.get(id);
        urlEntity.setViewCount(urlEntity.getViewCount()+1);
        this.save(urlEntity);
        return urlEntity;
    }
    private List<UrlEntity> isUrlExists(LongUrl longUrl) {
        return urlConverterRepository.findUrlByLongUrl(longUrl.getLongUrl());
    }
    private UrlEntity save(LongUrl longUrl) {
        return urlConverterRepository.save(new UrlEntity(longUrl.getLongUrl()));
    }
    private UrlEntity save(UrlEntity urlEntity) {
        return urlConverterRepository.save(urlEntity);
    }
    private UrlEntity get(Long id) {
        logger.info("Fetching Url from database for Id "+ id);
        UrlEntity urlEntity = urlConverterRepository.findById(id).get();
        return urlEntity;
    }

}
