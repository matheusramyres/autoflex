import {
  useReactTable,
  getCoreRowModel,
  getSortedRowModel,
  flexRender,
  type SortingState,
} from '@tanstack/react-table';

import { useState } from 'react';
import { columns } from './columns';
import { Edit2, Plus, Save, Trash2, X } from 'lucide-react';
import clsx from 'clsx';
import { useDispatch } from 'react-redux';
import type { AppDispatch } from '../../app/store';

import {
  createRawMaterial,
  updateRawMaterial,
  deleteRawMaterial,
} from '../../app/rawMaterialSilce';
import type { RawMaterial } from '../../types/rawMaterial';

type RawMaterialTableProps = {
  data: RawMaterial[];
};

export const MaterialTable = ({ data }: RawMaterialTableProps) => {
  const dispatch = useDispatch<AppDispatch>();
  const [sorting, setSorting] = useState<SortingState>([]);
  const [isAdding, setIsAdding] = useState(false);

  const [editingRawMaterial, setEditingRawMaterial] = useState<
    string | number | null
  >(null);
  const [editForm, setEditForm] = useState<Partial<RawMaterial>>({});

  const [addForm, setAddForm] = useState<Partial<RawMaterial>>({
    name: '',
    stockQuantity: 0,
  });

  const table = useReactTable({
    data,
    columns,
    state: { sorting },
    onSortingChange: setSorting,
    getCoreRowModel: getCoreRowModel(),
    getSortedRowModel: getSortedRowModel(),
  });

  const startEdit = (material: RawMaterial) => {
    setEditingRawMaterial(material.id);
    setEditForm({ ...material });
  };

  const handleDeleteMaterial = (rawMaterialId: number) => {
    dispatch(deleteRawMaterial(rawMaterialId));
  };

  const cancelEdit = () => {
    setEditingRawMaterial(null);
    setEditForm({});
  };

  const saveEdit = () => {
    if (editingRawMaterial && editForm.name && editForm.stockQuantity) {
      dispatch(
        updateRawMaterial({
          id: Number(editingRawMaterial),
          payload: {
            name: editForm.name,
            stockQuantity: Number(editForm.stockQuantity),
          },
        }),
      );

      setEditingRawMaterial(null);
      setEditForm({});
    }
  };

  const addRawMaterial = () => {
    if (addForm.name && addForm.stockQuantity) {
      dispatch(
        createRawMaterial({
          name: addForm.name,
          stockQuantity: addForm.stockQuantity,
        }),
      );

      setIsAdding(false);
      setAddForm({ name: '', stockQuantity: 0 });
    }
  };

  return (
    <>
      <div className="flex items-center justify-between">
        <header className="w-full flex flex-col mb-6">
          <h1 className="text-white text-[28px] font-bold">Materiais</h1>
          <p className="text-[#B0B0B0] text-sm font-normal">
            Gerencie seu estoque de matérias-primas
          </p>
        </header>
        <button
          onClick={() => setIsAdding(true)}
          className="min-w-40 flex items-center gap-2 px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition-colors"
        >
          <Plus size={20} />
          Add Material
        </button>
      </div>
      {isAdding && (
        <div className="bg-gray-800 rounded-lg p-6 border border-blue-600">
          <h3 className="text-lg font-semibold text-white mb-4">
            Novo Material
          </h3>
          <div className="grid grid-cols-1 md:grid-cols-2 gap-4 mb-4">
            <div>
              <label className="block text-gray-400 text-sm mb-2">Nome</label>
              <input
                type="text"
                value={addForm.name}
                onChange={(e) =>
                  setAddForm({ ...addForm, name: e.target.value })
                }
                className="w-full px-4 py-2 bg-gray-900 border border-gray-700 rounded-lg text-white focus:border-blue-600 focus:outline-none"
                placeholder="Insira o nome do material"
              />
            </div>
            <div>
              <label className="block text-gray-400 text-sm mb-2">
                Estoque
              </label>
              <input
                type="number"
                value={addForm.stockQuantity}
                onChange={(e) =>
                  setAddForm({
                    ...addForm,
                    stockQuantity: parseInt(e.target.value),
                  })
                }
                className="w-full px-4 py-2 bg-gray-900 border border-gray-700 rounded-lg text-white focus:border-blue-600 focus:outline-none"
                placeholder="0.00"
              />
            </div>
          </div>
          <div className="flex gap-2">
            <button
              onClick={addRawMaterial}
              className="px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition-colors"
            >
              Salvar
            </button>
            <button
              onClick={() => {
                setIsAdding(false);
                setAddForm({ name: '', stockQuantity: 0 });
              }}
              className="px-4 py-2 bg-gray-700 text-white rounded-lg hover:bg-gray-600 transition-colors"
            >
              Cancelar
            </button>
          </div>
        </div>
      )}
      <div className="w-full mt-6 bg-[#1E2939] rounded-[10px]">
        <div className="w-full h-2.5 bg-[#172030] rounded-t-[10px]"></div>
        <table className="w-full">
          <thead>
            {table.getHeaderGroups().map((headerGroup) => (
              <tr key={headerGroup.id}>
                {headerGroup.headers.map((header) => (
                  <th
                    key={header.id}
                    className="bg-[#172030] p-4 text-[#B0B0B0] text-sm text-left font-normal cursor-pointer"
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
            {table.getRowModel().rows.map((row) => (
              <>
                <tr key={row.id}>
                  {row.getVisibleCells().map((cell) => (
                    <td
                      key={cell.id}
                      className={clsx(
                        'border-t border-[#2D3849]',
                        'p-4 text-sm font-semibold',
                      )}
                    >
                      {editingRawMaterial === row.original.id &&
                      (cell.id.includes('name') ||
                        cell.id.includes('stockQuantity')) ? (
                        <input
                          type="text"
                          value={
                            cell.id.includes('name')
                              ? editForm.name
                              : editForm.stockQuantity
                          }
                          onChange={(e) => {
                            const label = cell.id.includes('name')
                              ? 'name'
                              : 'stockQuantity';
                            setEditForm({
                              ...editForm,
                              [label]: e.target.value,
                            });
                          }}
                          className="w-full px-3 py-1 bg-gray-900 border border-gray-700 rounded text-white focus:border-blue-600 focus:outline-none"
                        />
                      ) : (
                        <p
                          className={clsx(
                            cell.id.includes('rawMaterialQuantity') &&
                              'flex justify-center items-center p-2 w-8 h-6.75 rounded-2xl text-[#3EA2FF] bg-[#1C3460]',
                          )}
                        >
                          {flexRender(
                            cell.column.columnDef.cell,
                            cell.getContext(),
                          )}
                        </p>
                      )}

                      {cell.id.includes('actions') && (
                        <div className="flex items-center justify-end gap-2">
                          {editingRawMaterial === row.original.id ? (
                            <>
                              <button
                                onClick={saveEdit}
                                className="p-2 text-green-400 hover:bg-gray-700 rounded transition-colors"
                                title="Save"
                              >
                                <Save size={18} />
                              </button>
                              <button
                                onClick={cancelEdit}
                                className="p-2 text-gray-400 hover:bg-gray-700 rounded transition-colors"
                                title="Cancel"
                              >
                                <X size={18} />
                              </button>
                            </>
                          ) : (
                            <>
                              <button
                                onClick={() => startEdit(row.original)}
                                className="p-2 text-blue-400 hover:bg-gray-700 rounded transition-colors"
                                title="Edit"
                              >
                                <Edit2 size={18} />
                              </button>
                              <button
                                onClick={() =>
                                  handleDeleteMaterial(row.original.id)
                                }
                                className="p-2 text-red-400 hover:bg-gray-700 rounded transition-colors"
                                title="Delete"
                              >
                                <Trash2 size={18} />
                              </button>
                            </>
                          )}
                        </div>
                      )}
                    </td>
                  ))}
                </tr>
              </>
            ))}
          </tbody>
        </table>
      </div>
    </>
  );
};
