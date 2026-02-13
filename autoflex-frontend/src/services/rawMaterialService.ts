import type { RawMaterialPayload } from '../types/RawMaterial';
import { api } from './api';

export const rawMaterialService = {
  getAll: async () => {
    const { data } = await api.get('/raw-materials');
    return data;
  },

  getById: async (id: number) => {
    const { data } = await api.get(`/raw-materials/${id}`);
    return data;
  },

  create: async (payload: RawMaterialPayload) => {
    const { data } = await api.post('/raw-materials', payload);
    return data;
  },

  update: async (id: number, payload: RawMaterialPayload) => {
    const { data } = await api.put(`/raw-materials/${id}`, payload);
    return data;
  },

  delete: async (id: number) => {
    await api.delete(`/raw-materials/${id}`);
  },
};
