import { api } from './api';

export const fetchProductionSuggestions = async () => {
  const response = await api.get('/production/suggestions');
  return response.data;
};
