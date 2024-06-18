package org.launchcode.techjobs.persistent.controllers;
import org.apache.logging.log4j.LogManager;
import jakarta.validation.Valid;
import org.apache.logging.log4j.Logger;
import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.Job;
import org.launchcode.techjobs.persistent.models.Skill;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.launchcode.techjobs.persistent.models.data.JobRepository;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private JobRepository jobRepository;

    private static final Logger log = LogManager.getLogger(HomeController.class);



    @RequestMapping("")
    public String index(Model model) {
        model.addAttribute("title", "My Jobs");
        Iterable<Job> jobs = jobRepository.findAll();
        log.info("Number of  jobs: " + ((Collection<?>) jobs).size());
        model.addAttribute("jobs", jobs );

        return "index";
    }

    @GetMapping("add")
    public String displayAddJobForm(Model model) {
        model.addAttribute("job", new Job());

        Iterable<Skill> skillIterable = skillRepository.findAll();
        List<Skill> skills = new ArrayList<>();
        skillIterable.forEach((Skill skill) -> skills.add(skill));
        model.addAttribute("skills", skills);

        Iterable <Employer> employersIterable =  employerRepository.findAll();
        List<Employer> employers = new ArrayList<>();
        employersIterable.forEach((Employer employer) -> employers.add(employer));
        model.addAttribute("employers", employers);

        return "skills/add";
    }

    @PostMapping("/add")
    public String processAddJobForm(@Valid @ModelAttribute ("job") Job newJob,
                                    Errors errors, Model model, @RequestParam int employerId, @RequestParam List<Integer> skills) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Job");
            List <Employer> employers = (List<Employer>) employerRepository.findAll();
            model.addAttribute("employers", employers);


            return "skills/add";
        }

        Optional<Employer> optEmployer = employerRepository.findById(employerId);
        if (optEmployer.isPresent()) {
            Employer employer = optEmployer.get();
            newJob.setEmployer(employer);

        }

        List<Skill> skillObjs = (List<Skill>) skillRepository.findAllById(skills);
        newJob.setSkills(skillObjs);

        jobRepository.save(newJob);


        return "redirect:./";
    }


    @GetMapping("/view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {
        Optional<Job> optJob = jobRepository.findById(jobId);
        if(optJob.isPresent()) {
            Job job = optJob.get();
            model.addAttribute("job", job);
            return "view";
        }
        else {
            return "redirect:../";
        }

    }


}