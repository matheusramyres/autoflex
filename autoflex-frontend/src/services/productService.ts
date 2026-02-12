import { api } from './api';
import type { Product } from '../types/ProductType';

export const productService = {
  getProductsWithMaterials: async (): Promise<Product[]> => {
    const res = await api.get<Product[]>('/production/product-with-materials');
    return res.data;
  },

  createProduct: async (payload: { name: string; price: number }) => {
    await api.post('/products', payload);
  },

  updateProduct: async (
    id: number,
    payload: { name: string; price: number },
  ) => {
    await api.put(`/products/${id}`, payload);
  },

  deleteProduct: async (id: number) => {
    await api.delete(`/products/${id}`);
  },

  addMaterialToProduct: async (payload: {
    productId: number;
    rawMaterial: number;
    requiredQuantity: number;
  }) => {
    await api.post('/product-raw-material', payload);
  },

  removeMaterialFromProduct: async (productRawMaterialId: number) => {
    await api.delete(`/product-raw-material/product/${productRawMaterialId}`);
  },

  getProductMaterials: async (productId: number) => {
    const res = await api.get(`/product-raw-material/product/${productId}`);
    return res.data;
  },
};
