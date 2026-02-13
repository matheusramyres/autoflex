import { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { fetchRawMaterials } from '../app/rawMaterialSilce';
import type { AppDispatch, RootState } from '../app/store';
import { MaterialTable } from '../components/MaterialTable';

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
        <MaterialTable data={items} loading={loading} />
      </section>
    </>
  );
};
