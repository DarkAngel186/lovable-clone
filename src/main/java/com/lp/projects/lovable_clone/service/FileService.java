package com.lp.projects.lovable_clone.service;

import com.lp.projects.lovable_clone.dto.file.FileContentResponse;
import com.lp.projects.lovable_clone.dto.file.FileNode;

import java.util.List;

public interface FileService {

    List<FileNode> getFileTree(Long projectId, Long userId);

    FileContentResponse getFileContent(Long projectId, Long userId);
}
