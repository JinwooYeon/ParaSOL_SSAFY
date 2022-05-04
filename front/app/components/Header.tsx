import styled from "styled-components/native";
import { mainBlue } from "../color";

const Container = styled.View`
  margin-top: 10%;
  margin-bottom: 5%;
  margin-left: 5%;
  flex-direction: row;
`;

const HeaderText = styled.Text<{ blue: boolean }>`
  color: ${(props) => (props.blue ? mainBlue : "black")};
  font-size: 35px;
  font-weight: bold;
`;

const Header = () => (
  <Container>
    <HeaderText blue={false}>Para</HeaderText>
    <HeaderText blue={true}>SOL</HeaderText>
  </Container>
);

export default Header;
