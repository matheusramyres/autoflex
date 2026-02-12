import { configureStore } from '@reduxjs/toolkit';
import dashboardReducer from './dashboardSlice';
import productionReducer from './productionSlice';
import productsReducer from './productsSlice';

export const store = configureStore({
  reducer: {
    dashboard: dashboardReducer,
    production: productionReducer,
    products: productsReducer,
  },
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
