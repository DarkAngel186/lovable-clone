package com.lp.projects.lovable_clone.service.impl;

import com.lp.projects.lovable_clone.dto.file.FileContentResponse;
import com.lp.projects.lovable_clone.dto.file.FileNode;
import com.lp.projects.lovable_clone.service.FileService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public List<FileNode> getFileTree(Long projectId, Long userId) {
        return List.of();
    }

    @Override
    public FileContentResponse getFileContent(Long projectId, Long userId) {
        return null;
    }
}
