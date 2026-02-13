import { NavLink } from 'react-router-dom';
import { OptionDashboard } from '../components/OptionDashboard';

export const SideBar = () => {
  type variant = 'dashboard' | 'products' | 'materials';
  const menuItems = [
    { id: 'dashboard', label: 'Dashboard', to: '/' },
    { id: 'products', label: 'Produtos', to: '/products' },
    { id: 'materials', label: 'Materiais', to: '/materials' },
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
            <NavLink
              key={item.id}
              to={item.to}
              className="w-full flex items-center justify-center"
            >
              {({ isActive }) => (
                <OptionDashboard
                  title={item.label}
                  variant={item.id as variant}
                  active={isActive}
                />
              )}
            </NavLink>
          );
        })}
      </nav>
    </aside>
  );
};
