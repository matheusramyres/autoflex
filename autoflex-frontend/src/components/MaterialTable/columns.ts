import type { ColumnDef } from '@tanstack/react-table';
import type { RawMaterial } from '../../types/rawMaterial';

export const columns: ColumnDef<RawMaterial>[] = [
  {
    accessorKey: 'name',
    header: 'Material',
  },
  {
    accessorKey: 'stockQuantity',
    header: 'Quantidade em estoque',
  },
  // {
  //   accessorKey: 'status',
  //   header: 'Qtd Materiais',
  // },
  {
    accessorKey: 'actions',
    header: 'Ações',
  },
];
