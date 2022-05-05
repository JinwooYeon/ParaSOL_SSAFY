import { LogoContainer, LogoText } from "../screens/styled";

const Logo = () => (
  <LogoContainer>
    <LogoText blue={false}>Para</LogoText>
    <LogoText blue={true}>SOL</LogoText>
  </LogoContainer>
);

export default Logo;
