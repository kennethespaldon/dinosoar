import React from "react";

function FlightLogMenu({ setSelectedTab }: { setSelectedTab: React.Dispatch<React.SetStateAction<string>> }) {
    return (
      <div>
          <button onClick={() => setSelectedTab('none')}>Back to Main Menu</button>
          {/*return Flights tabs (About, Action Items, Things Done Well, Things To Improve, Aircraft)*/}
      </div>
    );
}

export default FlightLogMenu;