import {
  flexRender,
  getCoreRowModel,
  getSortedRowModel,
  useReactTable,
  type SortingState,
} from '@tanstack/react-table';
import { clsx } from 'clsx';
import { useState } from 'react';
import { SkeletonRow } from '../../skeleton/SkeletonRow/SkeletonRow';
import type { ProductionSuggestion } from '../../types/production';
import { columns } from './columns';

type ProductionProps = {
  data: ProductionSuggestion[];
  loading: boolean;
};

export function ProductionSuggestionTable({ data, loading }: ProductionProps) {
  const [sorting, setSorting] = useState<SortingState>([]);

  const table = useReactTable({
    data,
    columns,
    state: { sorting },
    onSortingChange: setSorting,
    getCoreRowModel: getCoreRowModel(),
    getSortedRowModel: getSortedRowModel(),
  });

  return (
    <div className="w-full mt-6 bg-[#1E2939] rounded-[10px]">
      <header className="pl-5 pt-6.5 pb-6 border-b border-[#2D3849]">
        <p className="text-[20px] text-white font-bold">
          Sugestões de Produção
        </p>
        <p className="text-sm text-[#B0B0B0] font-normal">
          Recomendações de produção otimizadas com base nos materiais
          disponíveis.
        </p>
      </header>
      <table className="w-full">
        <thead>
          {table.getHeaderGroups().map((headerGroup) => (
            <tr key={headerGroup.id}>
              {headerGroup.headers.map((header) => (
                <th
                  key={header.id}
                  className={clsx(
                    'bg-[#172030] p-2 cursor-pointer',
                    'text-[#B0B0B0] text-sm text-left font-normal',
                  )}
                  onClick={header.column.getToggleSortingHandler()}
                >
                  {flexRender(
                    header.column.columnDef.header,
                    header.getContext(),
                  )}
                  {{
                    asc: ' 🔼',
                    desc: ' 🔽',
                  }[header.column.getIsSorted() as string] ?? null}
                </th>
              ))}
            </tr>
          ))}
        </thead>

        <tbody>
          {loading ? (
            <>
              {Array.from({ length: 6 }).map((_, i) => (
                <SkeletonRow key={i} />
              ))}
            </>
          ) : (
            table.getRowModel().rows.map((row) => (
              <tr key={row.id}>
                {row.getVisibleCells().map((cell) => (
                  <td
                    key={cell.id}
                    className={clsx(
                      'border-t border-[#2D3849]',
                      'p-4 text-sm font-semibold',
                      cell.id.includes('totalValue') ? 'text-[#47A2F9]' : '',
                    )}
                  >
                    {flexRender(cell.column.columnDef.cell, cell.getContext())}
                  </td>
                ))}
              </tr>
            ))
          )}
        </tbody>
      </table>
    </div>
  );
}
