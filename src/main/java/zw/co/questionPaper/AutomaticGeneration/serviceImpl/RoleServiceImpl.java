package zw.co.questionPaper.AutomaticGeneration.serviceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zw.co.questionPaper.AutomaticGeneration.repository.RoleRepository;
import zw.co.questionPaper.AutomaticGeneration.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;


}
