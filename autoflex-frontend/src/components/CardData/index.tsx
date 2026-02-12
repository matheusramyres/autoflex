import { clsx } from 'clsx';
import { DollarSign, TrendingUp, Box, Activity } from 'lucide-react';
import { moneyFormatter } from '../../utils/formatters';

interface CardProps {
  variant: 'money' | 'material' | 'product';
  value: number | undefined;
}

export const CardData = ({ value, variant }: CardProps) => {
  return (
    <article className={clsx(' w-full px-6 py-4.5', 'bg-[#1E2939] rounded-xl')}>
      <div className="flex justify-between items-center mb-4.5">
        <div className="w-12 h-12 rounded-lg bg-[#1C3460] flex justify-center items-center">
          {variant === 'money' ? (
            <DollarSign size={24} color="#50A2FF" />
          ) : variant === 'product' ? (
            <Box size={24} color="#50A2FF" />
          ) : (
            <Activity size={24} color="#50A2FF" />
          )}
        </div>
        <div className="flex justify-center text-[#05DF72]">
          <TrendingUp />
          <span className="ml-1.5">+12.5%</span>
        </div>
      </div>
      <div>
        <p className="text-sm text-[#99A1AF] font-semibold mb-2">
          {variant === 'money'
            ? 'Valor Total da Produção'
            : variant === 'product'
              ? 'Produtos Ativos'
              : 'Utilização de Materiais'}
        </p>
        <p className="text-[28px] text-white font-bold">
          {variant === 'money'
            ? `${moneyFormatter(value)}`
            : variant === 'product'
              ? value
              : `${value}%`}
        </p>
      </div>
    </article>
  );
};
