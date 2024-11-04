package org.project.springweb.service;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.project.springweb.dto.category.CategoryDto;
import org.project.springweb.dto.category.CreateCategoryRequestDto;
import org.project.springweb.mapper.CategoryMapper;
import org.project.springweb.model.Category;
import org.project.springweb.repository.category.CategoryRepository;
import org.project.springweb.service.category.CategoryServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {
    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryMapper categoryMapper;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Test
    @DisplayName("Find all categories")
    public void findAll_ValidPageable_ReturnsAllCategories() {
        //Given
        Category firstCategory = new Category();
        Category secondCategory = new Category();
        Page<Category> categoryPage = new PageImpl<>(List.of(firstCategory, secondCategory));
        Pageable pageable = Pageable.ofSize(1);
        CategoryDto firstCategoryDto = new CategoryDto();
        CategoryDto secondCategoryDto = new CategoryDto();
        final List<CategoryDto> expected = List.of(firstCategoryDto, secondCategoryDto);
        //When
        when(categoryMapper.toDto(firstCategory)).thenReturn(firstCategoryDto);
        when(categoryMapper.toDto(secondCategory)).thenReturn(secondCategoryDto);
        when(categoryRepository.findAll(pageable)).thenReturn(categoryPage);
        List<CategoryDto> actual = categoryService.findAll(pageable);
        //Then
        Assertions.assertEquals(expected, actual);
        verify(categoryMapper, Mockito.times(1)).toDto(firstCategory);
        verify(categoryMapper, Mockito.times(1)).toDto(secondCategory);
        verify(categoryRepository, Mockito.times(1)).findAll(pageable);
    }

    @Test
    @DisplayName("Find category by id")
    public void getById_WithValidId_ReturnsCategoryDto() {
        //Given
        Long categoryId = 1L;
        Category category = new Category();
        CategoryDto expected = new CategoryDto();
        //When
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));
        when(categoryMapper.toDto(category)).thenReturn(expected);
        CategoryDto actual = categoryService.getById(categoryId);
        //Then
        Assertions.assertEquals(expected, actual);
        verify(categoryMapper, Mockito.times(1)).toDto(category);
        verify(categoryRepository, Mockito.times(1)).findById(categoryId);
    }

    @Test
    @DisplayName("Save category")
    public void save_ValidCreateCategoryRequest_ReturnsCategoryDto() {
        //Given
        CreateCategoryRequestDto requestDto = new CreateCategoryRequestDto();
        Category category = new Category();
        CategoryDto expected = new CategoryDto();
        //When
        when(categoryMapper.toEntity(requestDto)).thenReturn(category);
        when(categoryRepository.save(category)).thenReturn(category);
        when(categoryMapper.toDto(category)).thenReturn(expected);
        CategoryDto actual = categoryService.save(requestDto);
        //Then
        Assertions.assertEquals(expected, actual);
        verify(categoryMapper, Mockito.times(1)).toEntity(requestDto);
        verify(categoryMapper, Mockito.times(1)).toDto(category);
        verify(categoryRepository, Mockito.times(1)).save(category);
    }

    @Test
    @DisplayName("Update category")
    public void update_ValidUpdateCategoryRequest_ReturnsCategoryDto() {
        //Given
        Long categoryId = 1L;
        CreateCategoryRequestDto requestDto = new CreateCategoryRequestDto();
        Category category = new Category();
        CategoryDto expected = new CategoryDto();
        //When
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));
        doNothing().when(categoryMapper).updateCategory(requestDto, category);
        when(categoryRepository.save(category)).thenReturn(category);
        when(categoryMapper.toDto(category)).thenReturn(expected);
        CategoryDto actual = categoryService.update(categoryId, requestDto);
        //Then
        Assertions.assertEquals(expected, actual);
        verify(categoryMapper, Mockito.times(1)).updateCategory(requestDto, category);
        verify(categoryMapper, Mockito.times(1)).toDto(category);
        verify(categoryRepository, Mockito.times(1)).save(category);
    }
}
