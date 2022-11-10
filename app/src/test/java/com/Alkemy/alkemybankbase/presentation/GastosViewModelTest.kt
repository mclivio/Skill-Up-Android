package com.Alkemy.alkemybankbase.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.Alkemy.alkemybankbase.repository.FakeGastosRepository
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule

class GastosViewModelTest{
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    //Needed Rule to test LiveData changes

    private lateinit var viewModel : ExpensesViewModel

    @Before
    fun setup() {
        viewModel = ExpensesViewModel(FakeGastosRepository())
    }
}