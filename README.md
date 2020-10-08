# Buscador de Cartas de Pokemon

[![Build Status](https://app.bitrise.io/app/6af20d613b256a46/status.svg?token=k8HVpWL5aQTU_9TrTgxb4Q&branch=master)](https://app.bitrise.io/app/6af20d613b256a46)

Este é um aplicativo nativo Android estilo showcase para testes / aplicações de funcionalidades.

Deve ser utilizado como consulta de implementações e referência de evolução das plataformas, framework e bibliotecas.

Não deve ser usado como "base" para nenhum projeto, mas sim como consulta porque é certo que irá apresentar mais funciondalidades do que esperado ou até alguns pontos de overengineering,

## Core:

Um aplicativo que faz a busca de uma lista de cartas de Pokemon

## Funcionalidades:

- [x] Listar Cartas
- [X] Exibir em formato de um grid

## Tecnologia / Stack:

- [x] Clean Architecture
- [x] MVVM
- [x] View - Activities + Layouts
- [x] Presenter - ViewModel (com propriedades reativas)
- [x] Router - Manual (modularizado)
- [x] Testes Unitários
- [x] Testes Testes de Integração (API)
- [x] Gerenciador de Dependências - Gradle + Groove
- [ ] Modularização - Algumas partes

## Bibliotecas Utilizadas:

- [x] LiveData / Observables
- [x] Glide
- [x] DI - Koin
- [x] MockK
- [x] Retrofit

## Work in Progress (WIP):

- [ ] Pipeline - Em construção

## Trabalho Futuro (Roadmap):

- [ ] Analytics
- [ ] Persistência - Room
- [ ] Gerenciador de Dependências - Gradle + Kotlin DSL
- [ ] View - Activitis + Fragments
- [ ] Router - Manual -> Navigation + NavHostFragment
- [ ] Certificate Pinning
- [ ] Padrão de Qualidade (QA) - DETEKT
