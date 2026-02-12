import { CardData } from '../components/CardData';
import { SuggestionMocke } from '../mock/SuggestionMock';
import { ProductionSuggestionTable } from '../components/SuggestionTable';

export const RawMaterials = () => {
  return (
    <>
      <header className="w-full flex flex-col mb-6">
        <h1 className="text-white text-[28px] font-bold">Materiais</h1>
        <p className="text-[#B0B0B0] text-sm font-normal">
          Gerencie seu estoque de matérias-primas
        </p>
      </header>
      {/* <section className="flex justify-between gap-6">
        <CardData variant="money" value="249.135,00" />
        <CardData variant="product" value="124" />
        <CardData variant="material" value="87.3" />
      </section> */}
      <section>
        <ProductionSuggestionTable data={SuggestionMocke} />
      </section>
    </>
  );
};
