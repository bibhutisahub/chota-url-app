package com.bsahu.chotaurl.service;

import java.util.List;

import com.bsahu.chotaurl.bo.LongUrl;
import com.bsahu.chotaurl.bo.ShortUrl;
import com.bsahu.chotaurl.model.UrlEntity;

/**
 * @author bsahu
 *
 */
public interface UrlConverterService {
	public LongUrl getLongUrl(String shortenString);
	public ShortUrl getShortUrl(LongUrl longUrl);
	public List<UrlEntity> getAllUrls();
}
