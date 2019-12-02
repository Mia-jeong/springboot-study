package com.mia.eatgo.application;

import com.mia.eatgo.domain.Category;
import com.mia.eatgo.domain.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class CategoryServiceTest {

    @Mock
    CategoryService categoryService;

    @Mock
    CategoryRepository categoryRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        categoryService = new CategoryService(categoryRepository);
    }

    @Test
    public void getCategories(){
        List<Category> mockCategories = new ArrayList<>();
        mockCategories.add(Category.builder().name("Chicken").build());

        given(categoryRepository.findAll()).willReturn(mockCategories);
        List<Category> categories = categoryService.getCategories();
        Category category = categories.get(0);

        assertThat(category.getName(), is("Chicken"));
    }
}