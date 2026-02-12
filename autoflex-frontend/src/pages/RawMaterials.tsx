import { MaterialTable } from '../components/MaterialTable';
import { useEffect } from 'react';
import { fetchRawMaterials } from '../app/rawMaterialSilce';
import { useDispatch, useSelector } from 'react-redux';
import type { AppDispatch, RootState } from '../app/store';

export const RawMaterials = () => {
  const dispatch = useDispatch<AppDispatch>();
  const { items, loading } = useSelector(
    (state: RootState) => state.rawMaterials,
  );

  useEffect(() => {
    dispatch(fetchRawMaterials());
  }, [dispatch]);
  return (
    <>
      <section>
        {items && items.length && <MaterialTable data={items} />}
      </section>
    </>
  );
};
