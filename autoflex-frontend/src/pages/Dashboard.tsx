import { CardData } from '../components/CardData';
import { ProductionSuggestionTable } from '../components/SuggestionTable';
import { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import type { AppDispatch, RootState } from '../app/store';
import { loadDashboard } from '../app/dashboardSlice';
import { loadProduction } from '../app/productionSlice';

export const Dashboard = () => {
  const dispatch = useDispatch<AppDispatch>();

  useEffect(() => {
    dispatch(loadDashboard());
    dispatch(loadProduction());
  }, [dispatch]);

  const { loading, summary } = useSelector(
    (state: RootState) => state.dashboard,
  );
  const { result } = useSelector((state: RootState) => state.production);

  const suggestions = result?.length ? result : [];

  return (
    <>
      <header className="w-full flex flex-col mb-6">
        <h1 className="text-white text-[28px] font-bold">Dashboard</h1>
        <p className="text-[#B0B0B0] text-sm font-normal">
          Visão geral das suas operações industriais
        </p>
      </header>
      <section className="flex justify-between gap-6">
        <CardData variant="money" value={summary?.totalProductionValue} />
        <CardData variant="product" value={summary?.activeProducts} />
        <CardData variant="material" value={summary?.materialUtilization} />
      </section>
      <section>
        {suggestions && suggestions.length && (
          <ProductionSuggestionTable data={suggestions} />
        )}
      </section>
    </>
  );
};
