package com.example.demo.Service.ServiceImpl;

import com.example.demo.Entity.SizeEntity;
import com.example.demo.Repository.SizeRepository;
import com.example.demo.Service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SizeServiceImpl implements SizeService {
    @Autowired
    SizeRepository sizeRepository;

    @Override
    public List<SizeEntity> getAllSize() {
        return sizeRepository.findAll();
    }

    @Override
    public SizeEntity getSizeById(int id) {
        Optional<SizeEntity> sizeOptional = sizeRepository.findById(id);
        SizeEntity sizeEntity = null;
        if (sizeOptional.isPresent()) {
            sizeEntity = sizeOptional.get();
        } else {
            throw new RuntimeException("Khong tim thay size" + id);
        }
        return sizeEntity;
    }

    @Override
    public void saveSize(SizeEntity sizeEntity) {
        this.sizeRepository.save(sizeEntity);
    }

    @Override
    public void deleteSize(SizeEntity sizeEntity) {
        this.sizeRepository.delete(sizeEntity);
    }

    @Override
    public Page<SizeEntity> pageSize(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        return sizeRepository.findAll(pageable);
    }
}
