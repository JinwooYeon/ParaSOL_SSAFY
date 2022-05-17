import styled from "styled-components";
import { FooterImageComponent } from "components/footer/footerCompo/FooterImageComponent";
// import { FooterInfoComponent } from "components/footer/footerCompo/FooterInfoComponent";
import { FooterTeamComponent } from "components/footer/footerCompo/FooterTeamComponent";

export const Footer = () => {
  return (
    <FooterDiv>
      <div
        style={{
          display: "flex",
          flexDirection: "column",
          justifyContent: "center",
          alignItems: "center",
          width: "10%",
        }}
      >
        <FooterTeamComponent />
        <FooterImageComponent />
      </div>
      <div
        style={{
          display: "flex",
          flexDirection: "row",
          justifyContent: "center",
          alignItems: "center",
          gap: 30,
          width: "80%",
        }}
      >
        {/* <FooterInfoComponent /> */}
        <div>@ 2022 TEAM ParaSOL</div>
      </div>
    </FooterDiv>
  );
};

const FooterDiv = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 50px 10px 30px;
  gap: 10px;
  font-size: 13px;
`;
