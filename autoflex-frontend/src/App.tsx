import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { Toaster } from 'sonner';
import './App.css';
import { Dashboard } from './pages/Dashboard';
import { Products } from './pages/Products';
import { RawMaterials } from './pages/RawMaterials';
import { SideBar } from './pages/SideBar';

function App() {
  return (
    <BrowserRouter>
      <div className="w-full min-h-screen flex ">
        <Toaster position="top-right" />
        <SideBar />
        <main className="ml-64 mb-10 w-full px-6 pt-8">
          <Routes>
            <Route path="/" element={<Dashboard />} />
            <Route path="/products" element={<Products />} />
            <Route path="/materials" element={<RawMaterials />} />
          </Routes>
        </main>
      </div>
    </BrowserRouter>
  );
}

export default App;
