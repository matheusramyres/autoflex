export const CardDataSkeleton = () => {
  return (
    <article className="w-full px-6 py-4.5 bg-[#1E2939] rounded-xl animate-pulse">
      <div className="flex justify-between items-center mb-4.5">
        <div className="w-12 h-12 rounded-lg bg-[#1C3460]" />

        <div className="flex items-center gap-2">
          <div className="w-5 h-5 rounded bg-[#1C3460]" />
          <div className="w-12 h-4 rounded bg-[#1C3460]" />
        </div>
      </div>

      <div>
        <div className="w-40 h-4 rounded bg-[#1C3460] mb-3" />

        <div className="w-32 h-8 rounded bg-[#1C3460]" />
      </div>
    </article>
  );
};
