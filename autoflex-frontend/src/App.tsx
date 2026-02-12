import { useState, type JSX } from 'react';
import { Dashboard } from './pages/Dashboard';
import './App.css';
import { SideBar } from './pages/SideBar';
import { Products } from './pages/Products';
import { RawMaterials } from './pages/RawMaterials';

function App() {
  const [currentScreen, setCurrentScreen] = useState('dashboard');

  const renderScreen = () => {
    switch (currentScreen) {
      case 'dashboard':
        return <Dashboard />;
      case 'products':
        return <Products />;
      case 'materials':
        return <RawMaterials />;
      default:
        return <Dashboard />;
    }
  };
  return (
    <div className="w-full min-h-screen flex ">
      <SideBar currentScreen={currentScreen} onNavigate={setCurrentScreen} />
      <main className="ml-64 mb-10 w-full px-6 pt-8">{renderScreen()}</main>
    </div>
  );
}

export default App;
