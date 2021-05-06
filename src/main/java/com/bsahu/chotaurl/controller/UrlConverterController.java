package com.bsahu.chotaurl.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.routines.UrlValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import com.bsahu.chotaurl.bo.LongUrl;
import com.bsahu.chotaurl.bo.ShortUrl;
import com.bsahu.chotaurl.model.UrlEntity;
import com.bsahu.chotaurl.service.UrlConverterService;
import com.bsahu.chotaurl.util.UrlConversionUtil;




/**
 * @author bsahu
 *
 */
@RestController
public class UrlConverterController {

    Logger logger = LoggerFactory.getLogger(UrlConverterController.class);
    
    @Value("${spring.application.name}")
    String appName;
    
    @Autowired
    UrlConverterService urlConverterService;

    
	@GetMapping("/")
    public ModelAndView homePage(Model model) {
	    logger.info("UrlConverterController.homePage()");
        model.addAttribute("appName", appName);
        return new ModelAndView("home");
    }
	@GetMapping("/getUrlList")
    public ModelAndView getUrlList(Model model) {
	    logger.info("UrlConverterController.getUrlList()");
	    List<UrlEntity> list=urlConverterService.getAllUrls();
	    model.addAttribute("appName", appName);
	    model.addAttribute("urlList", list);
        return new ModelAndView("list");
    }
    @PostMapping("/convertUrl")
    public ModelAndView convertAndSave(@ModelAttribute LongUrl longUrl, Model model,HttpServletRequest request) {

    	logger.info("Url :"+ longUrl.getLongUrl());
        UrlValidator validator = new UrlValidator( new String[]{"http", "https"});
        String url = longUrl.getLongUrl();
        
        if (!validator.isValid(url)) {
            logger.error("Invalid Url provided");
    		model.addAttribute("shortUrl", "Invalid URL provided !!");
    		return new ModelAndView("home");
        }
        
        String baseUrl = null;

        try {
            baseUrl = UrlConversionUtil.getBaseUrl(request.getRequestURL().toString());
        } catch (MalformedURLException e) {
            logger.error("Malformed request url");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request url is invalid", e);
        }

        // Retrieving the Shortened url and concatenating with protocol://domain:port
        ShortUrl shortUrl = urlConverterService.getShortUrl(longUrl);
        shortUrl.setShortUrl(baseUrl + shortUrl.getShortUrl());

        logger.info("ShortUrl generated :"+ shortUrl.getShortUrl());

		model.addAttribute("shortUrl", shortUrl.getShortUrl());
		return new ModelAndView("home");
    }


    @GetMapping("getUrl/{shortenString}")
    public void redirectToLongUrl(HttpServletResponse response, @PathVariable String shortenString) {
        try {
            LongUrl fullUrl = urlConverterService.getLongUrl(shortenString);

            logger.info("Redirecting to "+ fullUrl.getLongUrl());
            response.sendRedirect(fullUrl.getLongUrl());
            
        } catch (NoSuchElementException e) {
            logger.error("No URL found for" + shortenString);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Url not found", e);
        } catch (IOException e) {
            logger.error("Could not redirect to the full url");
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Could not redirect to the full url", e);
        }
    }

}
