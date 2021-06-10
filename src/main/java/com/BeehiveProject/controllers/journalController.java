package com.BeehiveProject.controllers;

import com.BeehiveProject.models.Journal;
import com.BeehiveProject.repo.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;

@Controller
public class journalController {

    @Autowired
    private JournalRepository journalRepository;

    @GetMapping("/journal")
    public String journal(Model model) {
        model.addAttribute("title", "Журнал пасеки");
        Iterable<Journal> journals = journalRepository.findAll();
        model.addAttribute("journals", journals);
        return "journal";
    }
    @GetMapping("/journal/add")
    public String journalAdd(Model model) {
        model.addAttribute("title", "Добавление записи");
        return "journalAdd";
    }
    @PostMapping("/journal/add")
    public String journalPostAdd(@RequestParam String title, @RequestParam String worker,Model model) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Journal journal = new Journal(timestamp, worker, title);
        journalRepository.save(journal);
        return "redirect:/journal";
    }
    @PostMapping("/journal/{id}/delete")
    public String deleteJournal(@PathVariable(value = "id") int id, Model model) {
        // Находим нужную запись по ID
        Journal journal = journalRepository.findById(id).orElseThrow();
        journalRepository.delete(journal); // Выполняем удаление

        // Возвращаем на главную страницу
        return "redirect:/journal";
    }
}
