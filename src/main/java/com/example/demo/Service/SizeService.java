package com.example.demo.Service;

import com.example.demo.Entity.SizeEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SizeService {
    public List<SizeEntity> getAllSize();

    public SizeEntity getSizeById(int id);

    void saveSize(SizeEntity sizeEntity);

    void deleteSize(SizeEntity sizeEntity);

    public Page<SizeEntity> pageSize(int pageNo, int pageSize);

}
