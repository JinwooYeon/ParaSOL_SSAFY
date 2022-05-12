import Box from "@mui/material/Box";
import Tab from "@mui/material/Tab";
import TabContext from "@mui/lab/TabContext";
import TabList from "@mui/lab/TabList";
import TabPanel from "@mui/lab/TabPanel";
import { useState } from "react";
import User from "pages/user";
import Account from "pages/account";
import Pay from "pages/pay";
import Header from "pages/header";

function App() {
  const [value, setValue] = useState("1");

  const handleChange = (event: React.SyntheticEvent, newValue: string) => {
    setValue(newValue);
  };

  return (
    <>
      <Header />
      <Box sx={{ width: "100%", typography: "body1" }}>
        <TabContext value={value}>
          <Box sx={{ borderBottom: 1, borderColor: "divider" }}>
            <TabList onChange={handleChange} aria-label="lab API tabs example">
              <Tab label="유저" value="1" />
              <Tab label="계좌" value="2" />
              <Tab label="인증 및 페이" value="3" />
            </TabList>
          </Box>
          {/* 유저 */}
          <TabPanel value="1">
            <User />
          </TabPanel>
          {/* 계좌 */}
          <TabPanel value="2">
            <Account />
          </TabPanel>
          {/* 인증 및 페이 */}
          <TabPanel value="3">
            <Pay />
          </TabPanel>
        </TabContext>
      </Box>
    </>
  );
}

export default App;
