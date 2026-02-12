import { OptionDashboard } from '../components/OptionDashboard';

interface SideBarProps {
  currentScreen: string;
  onNavigate: (screen: string) => void;
}

export const SideBar = ({ currentScreen, onNavigate }: SideBarProps) => {
  type variant = 'dashboard' | 'products' | 'materials';
  const menuItems = [
    { id: 'dashboard', label: 'Dashboard' },
    { id: 'products', label: 'Produtos' },
    { id: 'materials', label: 'Materiais' },
  ];
  return (
    <aside className="fixed max-w-3xs w-full min-h-screen bg-[#101828] flex flex-col items-center">
      <header className="mt-7 mb-10 w-full pl-6">
        <p className="text-xl text-white font-bold">ERP Industrial</p>
        <p className="text-sm text-[#B0B0B0] font-normal">
          Gerenciamento de Sistema
        </p>
      </header>
      <nav className="flex flex-col items-center w-full">
        {menuItems.map((item) => {
          return (
            <OptionDashboard
              title={item.label}
              variant={item.id as variant}
              active={currentScreen === item.id}
              onClick={() => {
                onNavigate(item.id);
              }}
            />
          );
        })}
      </nav>
    </aside>
  );
};
