package com.example.microservicios_cursos.app.cursos.services;

import org.springframework.stereotype.Service;

import com.example.commons_microservicios.commons.service.CommonServiceImpl;
import com.example.microservicios_cursos.app.cursos.models.entities.Curso;
import com.example.microservicios_cursos.app.cursos.repository.CursoRepository;

@Service
public class CursoServiceImpl extends CommonServiceImpl<Curso, CursoRepository> implements CursoService {



}
