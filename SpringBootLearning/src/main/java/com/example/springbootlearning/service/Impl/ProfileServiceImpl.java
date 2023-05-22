package com.example.springbootlearning.service.Impl;

import com.example.springbootlearning.Database.Entity.Profile;
import com.example.springbootlearning.Database.Repository.ProfileRepository;
import com.example.springbootlearning.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("ProfileService")
public class ProfileServiceImpl implements ProfileService {
    @Autowired
    private ProfileRepository profileRepository;
    @Override
    public List<Profile> findAll() {
        System.out.println(profileRepository.findAll());
        return profileRepository.findAll();
    }

    @Override
    public Profile save(Profile p) {
        return profileRepository.save(p);
    }

    @Override
    public Profile findOne(long id) {
        return profileRepository.findById(id).get();
    }

    @Override
    public void delete(long id) {
        profileRepository.deleteById(id);
    }

    @Override
    public List<Profile> findByJob(String job) {
        return profileRepository.findByJob(job);
    }

    @Override
    public List<Profile> findByJobAndStatus(String job, int status) {
        return profileRepository.findByJobAndStatus(job, status);
    }

    @Override
    public List<Profile> findByDescriptionEndsWith(String description) {
        return profileRepository.findByDescriptionEndsWith(description);
    }

    @Override
    public List<Profile> findByDescriptionContains(String description) {
        return profileRepository.findByDescriptionContains(description);
    }

    @Override
    public List<Profile> findByJPQL(int len) {
        return profileRepository.findJPQL(len);
    }

    @Override
    public int updateByJPQL(int status, long id) {
        return profileRepository.updateByJPQL(status, id);
    }

    @Override
    public int deleteByJPQL(long id) {
        return profileRepository.deleteByJPQL(id);
    }
}
