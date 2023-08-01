package com.APIQuiz.QuizAPI.services;

import com.APIQuiz.QuizAPI.entites.Utilisateur;
import com.APIQuiz.QuizAPI.repository.UtilisateurRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class UtilisateurServiceImpl implements IUtilisateurService{

    private UtilisateurRepository utilisateurRepository;    //  injection du repository user

    @Override
    public Utilisateur inscrire(Utilisateur utilisateur) {
        Utilisateur utilisateurVerif = utilisateurRepository.findByUsername(utilisateur.getUsername());
        if (utilisateurVerif == null){
            return utilisateurRepository.save(utilisateur);
        }else {
            throw new EntityNotFoundException("User existe deja !");
        }
    }

    @Override
    public String connexion(String username,String password) {
        Utilisateur user = utilisateurRepository.findByUsernameAndPassword(username,password);
        if (user!=null){
            return "Connexion reussit";
        }else {
            throw new EntityNotFoundException("User existe deja !");
        }
    }

    @Override
    public List<Utilisateur> afficher() {
        return utilisateurRepository.findAll();
    }

    @Override
    public Utilisateur lire(Long id) {
        return utilisateurRepository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException("Aucun client n'existe avec cette identifiant")
        );
    }

    @Override
    public void supprimer(Long idUser) {
        Utilisateur utilisateur = utilisateurRepository.findByIdUser(idUser);
        if (utilisateur!=null){
            utilisateurRepository.deleteById(idUser);
        }else {
            throw new EntityNotFoundException("id n'existe pas");
        }

    }

    @Override
    public Utilisateur modifier(Utilisateur user) {
        Utilisateur utilisateurVerif = utilisateurRepository.findByUsername(user.getUsername());
        if (utilisateurVerif == null){
            return utilisateurRepository.save(user);
        }else {
            throw new EntityNotFoundException("User existe deja !");
        }
    }

}
