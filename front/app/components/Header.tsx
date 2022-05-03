import styled from "styled-components/native";

const Container = styled.View`
  margin-top: 40px;
  margin-left: 20px;
  margin-bottom: 20px;
  flex-direction: row;
`;

const HeaderText = styled.Text<{ blue: boolean }>`
  color: ${(props) => (props.blue ? "#105cf0" : "black")};
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
