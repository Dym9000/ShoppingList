package com.example.shoppinglist.java;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.shoppinglist.scenes.shopping_lists.list_details.ShoppingDetailsViewModel;
import com.example.shoppinglist.scenes.shopping_lists.list_details.repository.ProductRepositoryImpl;

import org.jetbrains.annotations.NotNull;

public class ShoppingDetailsViewModelFactory implements ViewModelProvider.Factory {
    private final ProductRepositoryImpl mRepository;
    private final Long mItemId;
    private final Integer mIsArchived;

    public ShoppingDetailsViewModelFactory(ProductRepositoryImpl repository, Long itemId, Integer isArchived) {
        this.mRepository = repository;
        this.mItemId = itemId;
        this.mIsArchived = isArchived;
    }

    @NonNull
    @NotNull
    @Override
    public <T extends ViewModel> T create(@NonNull @NotNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ShoppingDetailsViewModel.class) && mItemId != null
                && mIsArchived != null) {
            return (T) new ShoppingDetailsViewModel(mRepository, mItemId, mIsArchived);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}