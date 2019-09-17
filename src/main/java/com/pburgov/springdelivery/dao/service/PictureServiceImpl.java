package com.pburgov.springdelivery.dao.service;

import com.pburgov.springdelivery.config.ConfigProperties;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PictureServiceImpl implements PictureService {
	
	private final Log logger = LogFactory.getLog(this.getClass());
	private ConfigProperties configProperties;


	@Autowired
    public PictureServiceImpl( ConfigProperties configProperties ) {
        this.configProperties = configProperties;
    }

    @Override
	public List<String> loadPictures(Long id) {
		List<String> namePictures = new ArrayList<>();
		Path p = Paths.get(configProperties.getFotosPath());
		File f = p.toFile();
		File[] matchingFiles = f.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {

				return name.startsWith(String.valueOf(id));
			}
		});

		if (matchingFiles != null) {
			for (File file : matchingFiles) {
				if (file.isFile()) {
					logger.info("Name:" + file.getName());
					namePictures.add(file.getName());
				}
			}
		}
		return namePictures;
	}



}
