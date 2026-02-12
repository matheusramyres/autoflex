import { ProductTable } from '../components/ProductTable';
import { useEffect } from 'react';
import { fetchProducts } from '../app/productsSlice';
import { useDispatch, useSelector } from 'react-redux';
import type { AppDispatch, RootState } from '../app/store';

export const Products = () => {
  const dispatch = useDispatch<AppDispatch>();
  const { items, loading } = useSelector((state: RootState) => state.products);

  useEffect(() => {
    dispatch(fetchProducts());
  }, [dispatch]);
  return (
    <>
      <section>
        {items && items.length && <ProductTable data={items} />}
      </section>
    </>
  );
};
