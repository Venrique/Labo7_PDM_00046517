package com.example.labo7_pdm_00046517.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.labo7_pdm_00046517.Database.GithubRepo
import com.example.labo7_pdm_00046517.Database.RoomDB
import com.example.labo7_pdm_00046517.Repository.GitHubRepoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GitHubRepoViewModel(app: Application): AndroidViewModel(app) {

    private val repository: GitHubRepoRepository

    init {
        val repoDao = RoomDB.getIntace(app).repoDao()
        repository = GitHubRepoRepository(repoDao)
    }

    fun insert(repo: GithubRepo) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(repo)
    }

    fun getAll():LiveData<List<GithubRepo>> = repository.getAll()

    fun nukeAll() = repository.nuke()
}