import Box from "@mui/material/Box";
import Tab from "@mui/material/Tab";
import TabContext from "@mui/lab/TabContext";
import TabList from "@mui/lab/TabList";
import TabPanel from "@mui/lab/TabPanel";
import { useEffect, useState } from "react";
import User from "pages/user";
import Account from "pages/account";
import Pay from "pages/pay";
import Header from "pages/header";
import Footer from "pages/footer";
import { Container } from "@mui/material";
import axios from "axios";

function App() {
  const [value, setValue] = useState("1");

  const handleChange = (event: React.SyntheticEvent, newValue: string) => {
    setValue(newValue);
  };

  const oauthPost = (data: string) => {
    const parseQuery = function () {
      const a = window.location.search.substring(1).split('&');
      if (a.length === 0) return {};
      let b = {};

      for (let i = 0; i < a.length; ++i) {
          const p = a[i].split('=', 2);

          b = {
            [decodeURIComponent(p[0])]: decodeURIComponent(p[1] || ''),
            ...b
          }
      }
      return b;
    };

    axios({
      method: "post",
      url: "/user/login/google/redirect",
      data: parseQuery(),
    })
      .then((response) => {
        console.log(response);
        localStorage.setItem("accessToken", response.data.accessToken);
        localStorage.setItem("refreshToken", response.data.refreshToken);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  useEffect(() => {
    oauthPost(window.location.search);
  }, []);

  return (
    <>
      {/* 헤더 */}
      <Header />
      <Container maxWidth="md">
        {/* 컨텐츠 */}
        <Box sx={{ width: "100%", typography: "body1" }}>
          <TabContext value={value}>
            <Box sx={{ borderBottom: 1, borderColor: "divider" }}>
              <TabList
                onChange={handleChange}
                aria-label="lab API tabs example"
              >
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
        {/* 푸터 */}
        <Footer />
      </Container>
    </>
  );
}

export default App;
